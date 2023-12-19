package com.bee.modules.security.rest;

import com.bee.common.exception.ErrorCode;
import com.bee.common.util.validator.AssertUtils;
import com.bee.modules.security.service.CaptchaService;
import com.bee.modules.security.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bruce
 * @create 2023/12/13
 * @description
 */
@RestController("/login")
@Api(tags = "登陆管理")
@AllArgsConstructor
public class LoginController {
    private final CaptchaService captchaService;
    private final SysUserTokenService sysUserTokenService;


    @GetMapping("/captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        captchaService.create(response, uuid);
    }
}
