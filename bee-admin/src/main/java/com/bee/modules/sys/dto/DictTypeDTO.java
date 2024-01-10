package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;

import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Bruce
 * @create 2024/01/09
 * @desc DictTypeDTO
 */
@ApiModel("DictTypeDTO")
public class DictTypeDTO implements Serializable {
    private static final long serialVersionUID = 270881480214509819L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;
    /**
    * 字典类型
    */
    @ApiModelProperty(value = "字典类型")
    @NotBlank(message = "{sysdict.type.require}", groups = DefaultGroup.class)
    private String dictType;
    /**
    * 字典名称
    */
    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "{sysdict.name.require}", groups = DefaultGroup.class)
    private String dictName;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "{sort.number}")
    private Integer sort;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}

