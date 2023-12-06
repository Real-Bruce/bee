package com.bee.common.aspect;

import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description 日志切面
 */
@Aspect
@Component
public class RedisAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${bee.redis.enable: false}")
    private boolean enable;

    @Around("execution(* com.bee.common.redis.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if (enable) {
            try {
                result = point.proceed();
            } catch (Exception e) {
                logger.error("redis error ", e);
                throw new BeeException(ErrorCode.REDIS_ERROR);
            }
        }
        return result;
    }
 }
