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
 * @desc SysLogLogin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_log_login")
public class SysLogLogin extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 245617141469058508L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 用户操作   0：用户登录   1：用户退出
    */
    public static final String OPERATION = "OPERATION";
    /**
    * 状态  0：失败    1：成功    2：账号已锁定
    */
    public static final String STATUS = "STATUS";
    /**
    * 用户代理
    */
    public static final String USER_AGENT = "USER_AGENT";
    /**
    * 操作IP
    */
    public static final String IP = "IP";
    /**
    * 用户名
    */
    public static final String CREATOR_NAME = "CREATOR_NAME";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 用户操作   0：用户登录   1：用户退出
    */
    @TableField("operation")
    private Integer operation;
    /**
    * 状态  0：失败    1：成功    2：账号已锁定
    */
    @TableField("status")
    private Integer status;
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
    * 用户名
    */
    @TableField("creator_name")
    private String creatorName;

}

