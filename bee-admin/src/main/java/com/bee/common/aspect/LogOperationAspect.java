package com.bee.common.aspect;

import com.bee.common.annotation.LogOperation;
import com.bee.common.util.common.HttpContextUtils;
import com.bee.common.util.common.IPUtils;
import com.bee.common.util.common.JsonUtils;
import com.bee.modules.log.dict.OperationStatusEnum;
import com.bee.modules.log.entity.SysLogOperation;
import com.bee.modules.log.service.SysLogOperationService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 操作日志切面
 */
@Aspect
@Component
@AllArgsConstructor
public class LogOperationAspect {
    private final SysLogOperationService sysLogOperationService;

    @Pointcut("@annotation(com.bee.common.annotation.LogOperation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            // 执行切面
            Object result = point.proceed();

            // 执行耗时ms
            long time = System.currentTimeMillis() - beginTime;

            saveLog(point, time, OperationStatusEnum.SUCCESS.getValue());

            return result;
        } catch (Exception e) {
            // 执行耗时ms
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            saveLog(point, time, OperationStatusEnum.FAIL.getValue());
            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint point, long time, int status) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);

        SysLogOperation logOperation = new SysLogOperation();
        if (Objects.nonNull(annotation)) {
            // 获取注解上的描述
            logOperation.setOperation(annotation.value());
        }

        logOperation.setStatus(status);
        logOperation.setRequestTime((int) time);

        // 请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        logOperation.setIp(IPUtils.getIpAddr(request));
        logOperation.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        logOperation.setRequestUri(request.getRequestURI());
        logOperation.setRequestMethod(request.getMethod());

        // 请求参数
        Object[] args = point.getArgs();
        String params = JsonUtils.toJsonString(args[0]);
        logOperation.setRequestParams(params);

        sysLogOperationService.save(logOperation);
    }

}
