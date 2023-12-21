package com.bee.modules.log.dto;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogOperationDTO
 */
@ApiModel("SysLogOperationDTO")
public class SysLogOperationDTO implements Serializable {
    private static final long serialVersionUID = 543805099306803315L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
    * 用户操作
    */
    @ApiModelProperty(value = "用户操作")
    private String operation;
    /**
    * 请求URI
    */
    @ApiModelProperty(value = "请求URI")
    private String requestUri;
    /**
    * 请求方式
    */
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;
    /**
    * 请求参数
    */
    @ApiModelProperty(value = "请求参数")
    private String requestParams;
    /**
    * 请求时长(毫秒)
    */
    @ApiModelProperty(value = "请求时长(毫秒)")
    private Integer requestTime;
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
    * 状态  0：失败   1：成功
    */
    @ApiModelProperty(value = "状态  0：失败   1：成功")
    private Integer status;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public Integer getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Integer requestTime) {
        this.requestTime = requestTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

