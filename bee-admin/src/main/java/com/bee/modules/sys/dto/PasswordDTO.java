package com.bee.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Bruce
 * @create 2024/01/09
 * @description 修改密码
 */
@Data
@ApiModel(value = "修改密码")
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 8039906659030778118L;

    @ApiModelProperty(value = "原始密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String password;

    @ApiModelProperty(value = "新密码|")
    @NotBlank(message = "{sysuser.password.require}")
    private String newPassword;
}
