package com.bee.modules.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bruce
 * @create 2023/12/13
 * @description 验证码生成服务
 */
public interface CaptchaService {

    /**
     * 生成验证码
     */
    void create(HttpServletResponse response, String uuid) throws IOException;

    /**
     * 验证码校验
     */
    boolean validate(String uuid, String code);
}
