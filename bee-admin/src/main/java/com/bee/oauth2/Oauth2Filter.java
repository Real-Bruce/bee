package com.bee.oauth2;

import cn.hutool.core.util.StrUtil;
import com.bee.common.constant.Constant;
import com.bee.common.exception.ErrorCode;
import com.bee.common.util.ResultVO;
import com.bee.common.util.common.HttpContextUtils;
import com.bee.common.util.common.JsonUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description oauth2过滤器
 */
public class Oauth2Filter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 获取请求的token
        String token = getRequestToken((HttpServletRequest) servletRequest);

        if (Objects.isNull(token)) {
            return null;
        }

        return new Oauth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 获取请求token，不存在直接返回401
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if (StrUtil.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = JsonUtils.toJsonString(new ResultVO<>().error(ErrorCode.UNAUTHORIZED));
            httpResponse.getWriter().print(json);
            return false;
        }

        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        try {
            Throwable throwable = Objects.isNull(e.getCause()) ? e : e.getCause();
            ResultVO<Object> error = new ResultVO<>().error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
            String json = JsonUtils.toJsonString(error);
            httpResponse.getWriter().print(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 获取请求中的token
     * @param request 请求
     * @return token
     */
    private String getRequestToken(HttpServletRequest request) {
        // 在header中获取token
        String token = request.getHeader(Constant.TOKEN_HEADER);

        // header中不存在则在参数中获取
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(Constant.TOKEN_HEADER);
        }

        return token;
    }
}
