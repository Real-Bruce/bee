package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Bruce
 * @create 2023/12/27
 * @desc SysRoleDTO
 */
@ApiModel("SysRoleDTO")
@Data
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = 947940620960266852L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @Null(message="{id.null}", groups = AddGroup.class)
    @NotNull(message="{id.require}", groups = UpdateGroup.class)
    private Long id;
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message="{sysrole.name.require}", groups = DefaultGroup.class)
    private String name;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 菜单ID列表
     */
    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuIdList;
    /**
     * 部门ID列表
     */
    @ApiModelProperty(value = "部门ID列表")
    private List<Long> deptIdList;
    /**
    * 创建者
    */
    @ApiModelProperty(value = "创建者")
    private Long creator;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

}

