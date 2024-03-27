package com.bee.modules.log.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bee.common.entity.BaseEntity;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogError
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_log_error")
public class SysLogError extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 811772866499001126L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 请求URI
    */
    public static final String REQUEST_URI = "REQUEST_URI";
    /**
    * 请求方式
    */
    public static final String REQUEST_METHOD = "REQUEST_METHOD";
    /**
    * 请求参数
    */
    public static final String REQUEST_PARAMS = "REQUEST_PARAMS";
    /**
    * 用户代理
    */
    public static final String USER_AGENT = "USER_AGENT";
    /**
    * 操作IP
    */
    public static final String IP = "IP";
    /**
    * 异常信息
    */
    public static final String ERROR_INFO = "ERROR_INFO";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 请求URI
    */
    @TableField("request_uri")
    private String requestUri;
    /**
    * 请求方式
    */
    @TableField("request_method")
    private String requestMethod;
    /**
    * 请求参数
    */
    @TableField("request_params")
    private String requestParams;
    /**
    * 用户代理
    */
    @TableField("user_agent")
    private String userAgent;
    /**
    * 操作IP
    */
    @TableField("ip")
    private String ip;
    /**
    * 异常信息
    */
    @TableField("error_info")
    private String errorInfo;

}

