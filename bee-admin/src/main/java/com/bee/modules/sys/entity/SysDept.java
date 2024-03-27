package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.bee.common.entity.BaseEntity;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDept
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@TableName(value = "sys_dept")
public class SysDept extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 410985401544669070L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 上级ID
    */
    public static final String PID = "PID";
    /**
    * 所有上级ID，用逗号分开
    */
    public static final String PIDS = "PIDS";
    /**
    * 部门名称
    */
    public static final String NAME = "NAME";
    /**
    * 排序
    */
    public static final String SORT = "SORT";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 上级ID
    */
    @TableField("pid")
    private Long pid;
    /**
    * 所有上级ID，用逗号分开
    */
    @TableField("pids")
    private String pids;
    /**
    * 部门名称
    */
    @TableField("name")
    private String name;
    /**
    * 排序
    */
    @TableField("sort")
    private Integer sort;
    /**
     * 上级部门名称
     */
    @TableField(exist = false)
    private String parentName;

}

