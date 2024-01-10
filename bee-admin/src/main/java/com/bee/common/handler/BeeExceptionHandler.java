package com.bee.common.handler;

import cn.hutool.core.map.MapUtil;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.exception.ExceptionUtils;
import com.bee.common.util.ResultVO;
import com.bee.common.util.common.HttpContextUtils;
import com.bee.common.util.common.IPUtils;
import com.bee.common.util.common.JsonUtils;
import com.bee.modules.log.entity.SysLogError;
import com.bee.modules.log.service.SysLogErrorService;
import com.qcloud.cos.Headers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/11
 * @description 异常处理器
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class BeeExceptionHandler {

    private final SysLogErrorService logErrorService;

    /**
     * 自定义异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BeeException.class)
    public ResultVO handleBeeException(BeeException ex) {
        return new ResultVO().error(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVO handleDuplicateKeyException(DuplicateKeyException ex) {
        return new ResultVO().error(ErrorCode.DB_RECORD_EXISTS);
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        saveLog(ex);

        return new ResultVO().error();
    }

    private void saveLog(Exception ex) {
        SysLogError logError = new SysLogError();

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        logError.setIp(IPUtils.getIpAddr(request));
        logError.setUserAgent(request.getHeader(Headers.USER_AGENT));
        logError.setRequestUri(request.getRequestURI());
        logError.setRequestMethod(request.getMethod());
        Map<String, String> params = HttpContextUtils.getParameterMap(request);

        if (MapUtil.isNotEmpty(params)) {
            logError.setRequestParams(JsonUtils.toJsonString(params));
        }

        logError.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

        logErrorService.save(logError);
    }

}
