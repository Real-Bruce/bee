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
 * @create 2023/12/20
 * @desc SysRoleUser
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_role_user")
public class SysRoleUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -96875828214584656L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 角色ID
    */
    public static final String ROLE_ID = "ROLE_ID";
    /**
    * 用户ID
    */
    public static final String USER_ID = "USER_ID";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 角色ID
    */
    @TableField("role_id")
    private Long roleId;
    /**
    * 用户ID
    */
    @TableField("user_id")
    private Long userId;

}

