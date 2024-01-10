package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc DictDataDTO
 */
@ApiModel("DictDataDTO")
public class DictDataDTO implements Serializable {
    private static final long serialVersionUID = 864004865817898900L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
    * 字典类型ID
    */
    @ApiModelProperty(value = "字典类型ID")
    private Long dictTypeId;
    /**
    * 字典标签
    */
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;
    /**
    * 字典值
    */
    @ApiModelProperty(value = "字典值")
    private String dictValue;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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

}

