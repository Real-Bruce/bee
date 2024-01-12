package com.bee.common.dynamic.datasource.aspect;

import com.bee.common.dynamic.datasource.annotation.DataSource;
import com.bee.common.dynamic.datasource.config.DynamicContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2024/01/12
 * @description 多数据源切面
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.bee.common.dynamic.datasource.annotation.DataSource) || @within(com.bee.common.dynamic.datasource.annotation.DataSource)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();

        DataSource targetDateSource = targetClass.getAnnotation(DataSource.class);
        DataSource methodDataSource = method.getAnnotation(DataSource.class);

        if (Objects.nonNull(targetDateSource) || Objects.nonNull(methodDataSource)) {
            String value;
            if (Objects.nonNull(methodDataSource)) {
                value = methodDataSource.value();
            } else {
                value = targetDateSource.value();
            }

            DynamicContextHolder.push(value);
            logger.debug("set datasource is {}", value);
        }

        try {
            return point.proceed();
        } finally {
            DynamicContextHolder.poll();
            logger.debug("clean datasource");
        }

    }
}
