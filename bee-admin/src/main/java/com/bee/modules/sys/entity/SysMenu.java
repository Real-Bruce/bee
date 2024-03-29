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
 * @desc SysMenu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_menu")
public class SysMenu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -76920270540404416L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 上级ID，一级菜单为0
    */
    public static final String PID = "PID";
    /**
    * 名称
    */
    public static final String NAME = "NAME";
    /**
    * 菜单URL
    */
    public static final String URL = "URL";
    /**
    * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
    */
    public static final String PERMISSIONS = "PERMISSIONS";
    /**
    * 类型   0：菜单   1：按钮
    */
    public static final String MENU_TYPE = "MENU_TYPE";
    /**
    * 菜单图标
    */
    public static final String ICON = "ICON";
    /**
    * 排序
    */
    public static final String SORT = "SORT";
    /**

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 上级ID，一级菜单为0
    */
    @TableField("pid")
    private Long pid;
    /**
    * 名称
    */
    @TableField("name")
    private String name;
    /**
    * 菜单URL
    */
    @TableField("url")
    private String url;
    /**
    * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
    */
    @TableField("permissions")
    private String permissions;
    /**
    * 类型   0：菜单   1：按钮
    */
    @TableField("menu_type")
    private Integer menuType;
    /**
    * 菜单图标
    */
    @TableField("icon")
    private String icon;
    /**
    * 排序
    */
    @TableField("sort")
    private Integer sort;
    /**
     * 上级菜单名称
     */
    @TableField(exist = false)
    private String parentName;
}

