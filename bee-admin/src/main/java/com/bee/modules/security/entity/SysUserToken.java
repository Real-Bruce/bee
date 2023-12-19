package com.bee.modules.security.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/11
 * @desc SysUserToken
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_user_token")
public class SysUserToken implements Serializable {
    private static final long serialVersionUID = 943255744507173589L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 用户id
    */
    public static final String USER_ID = "USER_ID";
    /**
    * 用户token
    */
    public static final String TOKEN = "TOKEN";
    /**
    * 过期时间
    */
    public static final String EXPIRE_DATE = "EXPIRE_DATE";
    /**
    * 更新时间
    */
    public static final String UPDATE_DATE = "UPDATE_DATE";
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
    * 用户id
    */
    @TableField("user_id")
    private Long userId;
    /**
    * 用户token
    */
    @TableField("token")
    private String token;
    /**
    * 过期时间
    */
    @TableField("expire_date")
    private Date expireDate;
    /**
    * 更新时间
    */
    @TableField("update_date")
    private Date updateDate;
    /**
    * 创建时间
    */
    @TableField("create_date")
    private Date createDate;

}

