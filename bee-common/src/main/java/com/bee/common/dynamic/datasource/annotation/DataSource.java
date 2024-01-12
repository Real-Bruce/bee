package com.bee.common.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * @author Bruce
 * @create 2024/01/12
 * @description 多数据源注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
