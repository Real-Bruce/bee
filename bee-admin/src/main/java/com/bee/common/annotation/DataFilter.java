package com.bee.common.annotation;

import java.lang.annotation.*;

/**
 * @author Bruce
 * @create 2023/12/11
 * @description 数据过滤注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {

    /**
     * 表别名
     */
    String tableAlias() default "";

    /**
     * 用户ID
     */
    String userId() default "";

    /**
     * 部门ID
     */
    String deptId() default "";
}
