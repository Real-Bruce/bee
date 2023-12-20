package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
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
public class SysDept implements Serializable {
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
    private Object sort;
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
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
    * 更新时间
    */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    /**
     * 上级部门名称
     */
    @TableField(exist = false)
    private String parentName;

}

