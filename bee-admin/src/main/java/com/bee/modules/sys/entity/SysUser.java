package com.bee.modules.sys.entity;

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
 * @create 2023/12/14
 * @desc SysUser
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_user")
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 469193531875820556L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 用户名
    */
    public static final String USERNAME = "USERNAME";
    /**
    * 密码
    */
    public static final String PASSWORD = "PASSWORD";
    /**
    * 姓名
    */
    public static final String REAL_NAME = "REAL_NAME";
    /**
    * 头像
    */
    public static final String HEAD_URL = "HEAD_URL";
    /**
    * 性别   0：男   1：女    2：保密
    */
    public static final String GENDER = "GENDER";
    /**
    * 邮箱
    */
    public static final String EMAIL = "EMAIL";
    /**
    * 手机号
    */
    public static final String MOBILE = "MOBILE";
    /**
    * 部门ID
    */
    public static final String DEPT_ID = "DEPT_ID";
    /**
    * 超级管理员   0：否   1：是
    */
    public static final String SUPER_ADMIN = "SUPER_ADMIN";
    /**
    * 状态  0：停用   1：正常
    */
    public static final String STATUS = "STATUS";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 用户名
    */
    @TableField("username")
    private String username;
    /**
    * 密码
    */
    @TableField("password")
    private String password;
    /**
    * 姓名
    */
    @TableField("real_name")
    private String realName;
    /**
    * 头像
    */
    @TableField("head_url")
    private String headUrl;
    /**
    * 性别   0：男   1：女    2：保密
    */
    @TableField("gender")
    private Integer gender;
    /**
    * 邮箱
    */
    @TableField("email")
    private String email;
    /**
    * 手机号
    */
    @TableField("mobile")
    private String mobile;
    /**
    * 部门ID
    */
    @TableField("dept_id")
    private Long deptId;
    /**
    * 超级管理员   0：否   1：是
    */
    @TableField("super_admin")
    private Integer superAdmin;
    /**
    * 状态  0：停用   1：正常
    */
    @TableField("status")
    private Integer status;
    /**
     * 部门名称
     */
    @TableField(exist=false)
    private String deptName;

}

