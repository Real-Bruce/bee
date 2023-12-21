package com.bee.modules.log.dto;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogErrorDTO
 */
@ApiModel("异常日志")
public class SysLogErrorDTO implements Serializable {
    private static final long serialVersionUID = 339146297719152624L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
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
    * 异常信息
    */
    @ApiModelProperty(value = "异常信息")
    private String errorInfo;
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

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}

