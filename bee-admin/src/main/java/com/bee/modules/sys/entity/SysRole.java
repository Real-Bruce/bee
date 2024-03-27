package com.bee.modules.sys.entity;

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
 * @desc SysRole
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@TableName(value = "sys_role")
public class SysRole extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 774235900336236712L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 角色名称
    */
    public static final String NAME = "NAME";
    /**
    * 备注
    */
    public static final String REMARK = "REMARK";
    /**
    * 部门ID
    */
    public static final String DEPT_ID = "DEPT_ID";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 角色名称
    */
    @TableField("name")
    private String name;
    /**
    * 备注
    */
    @TableField("remark")
    private String remark;
    /**
    * 部门ID
    */
    @TableField("dept_id")
    private Long deptId;

}

