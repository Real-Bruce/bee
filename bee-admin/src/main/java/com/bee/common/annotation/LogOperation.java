package com.bee.common.annotation;

import java.lang.annotation.*;

/**
 * @author Bruce
 * @create 2023/12/11
 * @description 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {

    String value() default "";
}
