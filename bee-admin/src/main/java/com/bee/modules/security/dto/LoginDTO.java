package com.bee.modules.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Bruce
 * @create 2023/12/13
 * @description 登录表单
 */
@Data
@ApiModel(value = "登录表单")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 7993233840462424898L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "{sysuser.username.require}")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "{sysuser.captcha.require}")
    private String captcha;

    @ApiModelProperty(value = "唯一标识符")
    @NotBlank(message = "{sysuser.uuid.require}")
    private String uuid;

}
