package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;

import com.bee.common.util.TreeNode;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.math3.analysis.function.Add;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Bruce
 * @create 2023/12/27
 * @desc SysMenuDTO
 */
@ApiModel("SysMenuDTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysMenuDTO extends TreeNode<SysMenuDTO> implements Serializable {
    private static final long serialVersionUID = -39666369258216352L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;
    /**
    * 上级ID，一级菜单为0
    */
    @ApiModelProperty(value = "上级ID，一级菜单为0")
    @NotNull(message = "{sysenum.pid.require}", groups = DefaultGroup.class)
    private Long pid;
    /**
    * 名称
    */
    @ApiModelProperty(value = "名称")
    @NotNull(message = "{sysmenu.name,require}", groups = DefaultGroup.class)
    private String name;
    /**
    * 菜单URL
    */
    @ApiModelProperty(value = "菜单URL")
    private String url;
    /**
    * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
    */
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;
    /**
    * 类型   0：菜单   1：按钮
    */
    @ApiModelProperty(value = "类型   0：菜单   1：按钮")
    @Range(min=0, max=1, message = "{sysmenu.type.range}", groups = DefaultGroup.class)
    private Integer menuType;
    /**
    * 菜单图标
    */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
    * 创建者
    */
    @ApiModelProperty(value = "创建者")
    private Long creator;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    /**
    * 更新者
    */
    @ApiModelProperty(value = "更新者")
    private Long updater;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "上级菜单名称")
    private String parentName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

