package com.bee.modules.log.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogOperation
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_log_operation")
public class SysLogOperation implements Serializable {
    private static final long serialVersionUID = 570419752913272848L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 用户操作
    */
    public static final String OPERATION = "OPERATION";
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
    * 请求时长(毫秒)
    */
    public static final String REQUEST_TIME = "REQUEST_TIME";
    /**
    * 用户代理
    */
    public static final String USER_AGENT = "USER_AGENT";
    /**
    * 操作IP
    */
    public static final String IP = "IP";
    /**
    * 状态  0：失败   1：成功
    */
    public static final String STATUS = "STATUS";
    /**
    * 用户名
    */
    public static final String CREATOR_NAME = "CREATOR_NAME";
    /**
    * 创建者
    */
    public static final String CREATOR = "CREATOR";
    /**
    * 创建时间
    */
    public static final String CREATE_DATE = "CREATE_DATE";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 用户操作
    */
    @TableField("operation")
    private String operation;
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
    * 请求时长(毫秒)
    */
    @TableField("request_time")
    private Integer requestTime;
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
    * 状态  0：失败   1：成功
    */
    @TableField("status")
    private Integer status;
    /**
    * 用户名
    */
    @TableField("creator_name")
    private String creatorName;
    /**
    * 创建者
    */
    @TableField("creator")
    private Long creator;
    /**
    * 创建时间
    */
    @TableField("create_date")
    private Date createDate;

}

