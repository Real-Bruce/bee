package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;

import com.bee.common.util.TreeNode;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDeptDTO
 */
@ApiModel("SysDeptDTO")
public class SysDeptDTO extends TreeNode implements Serializable {
    private static final long serialVersionUID = 748488362019011756L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;
    /**
    * 上级ID
    */
    @ApiModelProperty(value = "上级ID")
    @NotNull(message = "{sysDept.pid.require}", groups = DefaultGroup.class)
    private Long pid;
    /**
    * 部门名称
    */
    @ApiModelProperty(value = "部门名称")
    @NotBlank(message = "{sysdept.name.require}", groups = DefaultGroup.class)
    private String name;
    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
    private Integer sort;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @ApiModelProperty(value = "上级部门名称")
    private String parentName;

    public SysDeptDTO(Long id, Long pid, String name, Integer sort, Date createDate, String parentName) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.sort = sort;
        this.createDate = createDate;
        this.parentName = parentName;
    }

    public SysDeptDTO() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getPid() {
        return pid;
    }

    @Override
    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

