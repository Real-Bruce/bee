package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRole
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable {
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
    * 创建者
    */
    public static final String CREATOR = "CREATOR";
    /**
    * 创建时间
    */
    public static final String CREATE_DATE = "CREATE_DATE";
    /**
    * 更新者
    */
    public static final String UPDATER = "UPDATER";
    /**
    * 更新时间
    */
    public static final String UPDATE_DATE = "UPDATE_DATE";

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
    /**
    * 更新者
    */
    @TableField("updater")
    private Long updater;
    /**
    * 更新时间
    */
    @TableField("update_date")
    private Date updateDate;

}

