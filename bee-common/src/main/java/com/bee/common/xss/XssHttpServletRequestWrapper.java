package com.bee.common.xss;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/07
 * @description xxs 过滤处理
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest httpServletRequest;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        httpServletRequest = request;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 非JSON请求，默认返回
        if (!checkContentTypeIsJson()) {
            return super.getInputStream();
        }

        // 空请求直接返回
        String json = IoUtil.readUtf8(super.getInputStream());
        if (StrUtil.isBlank(json)) {
            return super.getInputStream();
        }

        // 进行xss过滤
        json = this.xssEncode(json);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(xssEncode(name));
        if (StrUtil.isNotBlank(parameter)) {
            parameter = xssEncode(parameter);
        }
        return parameter;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (Objects.isNull(parameters) || parameters.length == 0) {
            return null;
        }

        for (String parameter : parameters) {
            parameter = xssEncode(parameter);
        }

        return parameters;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new LinkedHashMap<>();
        Map<String, String[]> parameterMap = super.getParameterMap();

        for (String key : parameterMap.keySet()) {
            String[] parameters = parameterMap.get(key);
            for (String parameter : parameters) {
                parameter = xssEncode(parameter);
            }
            map.put(key, parameters);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        if (StrUtil.isNotBlank(header)) {
            header = xssEncode(header);
        }
        return header;
    }

    /**
     * 获取原始request
     * @param request
     */
    public static HttpServletRequest getOriginRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) request).getOriginRequest();
        }
        return request;
    }

    /**
     * 获取原始request
     */
    public HttpServletRequest getOriginRequest() {
        return httpServletRequest;
    }


    private String xssEncode(String input) {
        return XssUtils.filter(input);
    }

    /**
     * 根据前缀判断是否为JSON请求
     */
    private boolean checkContentTypeIsJson() {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        return StrUtil.startWithIgnoreCase(header, MediaType.APPLICATION_JSON_VALUE);
    }
}
