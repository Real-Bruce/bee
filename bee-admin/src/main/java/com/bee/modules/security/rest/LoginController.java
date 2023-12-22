package com.bee.modules.security.rest;

import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.util.ResultVO;
import com.bee.common.util.common.IPUtils;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.modules.log.dict.LoginOperationEnum;
import com.bee.modules.log.dict.LoginStatusEnum;
import com.bee.modules.log.entity.SysLogLogin;
import com.bee.modules.log.service.SysLogLoginService;
import com.bee.modules.security.dto.LoginDTO;
import com.bee.modules.security.password.PasswordUtils;
import com.bee.modules.security.service.CaptchaService;
import com.bee.modules.security.service.SysUserTokenService;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.UserStatusEnum;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

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
    private final SysUserService sysUserService;
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("/captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        captchaService.create(response, uuid);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultVO login(HttpServletRequest request, @RequestParam LoginDTO loginDTO) {

        // 校验验证码
        ValidatorUtils.validateEntity(loginDTO);

        boolean flag = captchaService.validate(loginDTO.getUuid(), loginDTO.getCaptcha());
        if (!flag) {
            return new ResultVO().error(ErrorCode.CAPTCHA_ERROR);
        }

        // 记录登录日志
        SysLogLogin log = new SysLogLogin();
        log.setOperation(LoginOperationEnum.LOGIN.getValue());
        log.setCreateDate(new Date());
        log.setIp(IPUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        SysUserDTO userDTO = sysUserService.getByUsername(loginDTO.getUserName());
        // 用户存在校验
        if (Objects.isNull(userDTO)) {
            log.setStatus(LoginStatusEnum.FAIL.getCode());
            log.setCreatorName(loginDTO.getUserName());
            sysLogLoginService.save(log);

            throw new BeeException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        // 密码校验
        if (!PasswordUtils.matchs(loginDTO.getPassword(), userDTO.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.getCode());
            log.setCreator(userDTO.getId());
            log.setCreatorName(userDTO.getUsername());
            sysLogLoginService.save(log);

            throw new BeeException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        // 账号状态校验
        if (UserStatusEnum.DISABLED.getValue() == userDTO.getStatus()) {
            log.setStatus(LoginStatusEnum.LOCK.getCode());
            log.setCreator(userDTO.getId());
            log.setCreatorName(userDTO.getUsername());
            sysLogLoginService.save(log);

            throw new BeeException(ErrorCode.ACCOUNT_DISABLE);
        }

        log.setStatus(LoginStatusEnum.SUCCESS.getCode());
        log.setCreator(userDTO.getId());
        log.setCreatorName(userDTO.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(userDTO.getId());

    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出")
    public ResultVO logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        sysUserTokenService.logout(user.getUserId());

        // 记录日志
        SysLogLogin log = new SysLogLogin();
        log.setOperation(LoginOperationEnum.LOGOUT.getValue());
        log.setIp(IPUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.getCode());
        log.setCreator(user.getUserId());
        log.setCreatorName(user.getUserName());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new ResultVO();

    }
}
