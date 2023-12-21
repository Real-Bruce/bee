package com.bee.modules.log.dto;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogLoginDTO
 */
@ApiModel("SysLogLoginDTO")
public class SysLogLoginDTO implements Serializable {
    private static final long serialVersionUID = -45360441566295612L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
    * 用户操作   0：用户登录   1：用户退出
    */
    @ApiModelProperty(value = "用户操作   0：用户登录   1：用户退出")
    private Integer operation;
    /**
    * 状态  0：失败    1：成功    2：账号已锁定
    */
    @ApiModelProperty(value = "状态  0：失败    1：成功    2：账号已锁定")
    private Integer status;
    /**
    * 用户代理
    */
    @ApiModelProperty(value = "用户代理")
    private String userAgent;
    /**
    * 操作IP
    */
    @ApiModelProperty(value = "操作IP")
    private String ip;
    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    private String creatorName;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}

