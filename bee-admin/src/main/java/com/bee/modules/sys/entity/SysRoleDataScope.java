package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleDataScope
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName(value = "sys_role_data_scope")
public class SysRoleDataScope implements Serializable {
    private static final long serialVersionUID = 780234444525098333L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 角色ID
    */
    public static final String ROLE_ID = "ROLE_ID";
    /**
    * 部门ID
    */
    public static final String DEPT_ID = "DEPT_ID";
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
    * 角色ID
    */
    @TableField("role_id")
    private Long roleId;
    /**
    * 部门ID
    */
    @TableField("dept_id")
    private Long deptId;
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

