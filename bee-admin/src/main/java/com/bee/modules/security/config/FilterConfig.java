package com.bee.modules.security.config;

import com.bee.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

/**
 * @author Bruce
 * @create 2023/12/13
 * @description Filter配置
 */
@Configuration
public class FilterConfig {

    @Bean("shiroFilterRegistration")
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");

        return registration;
    }

    @Bean("xssFilterRegistration")
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.setOrder(Integer.MAX_VALUE);
        registration.setName("xssFilter");
        registration.addUrlPatterns("/*");

        return registration;
    }
}
