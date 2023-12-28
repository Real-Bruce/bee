package com.bee.modules.sys.dto;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruce
 * @create 2023/12/27
 * @desc SysParamsDTO
 */
@ApiModel("SysParamsDTO")
public class SysParamsDTO implements Serializable {
    private static final long serialVersionUID = -13818123435162336L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
    * 参数编码
    */
    @ApiModelProperty(value = "参数编码")
    private String paramCode;
    /**
    * 参数值
    */
    @ApiModelProperty(value = "参数值")
    private String paramValue;
    /**
    * 类型   0：系统参数   1：非系统参数
    */
    @ApiModelProperty(value = "类型   0：系统参数   1：非系统参数")
    private Integer paramType;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
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

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

