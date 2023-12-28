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
 * @create 2023/12/28
 * @desc SysRoleMenu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -40256696509252915L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 角色ID
    */
    public static final String ROLE_ID = "ROLE_ID";
    /**
    * 菜单ID
    */
    public static final String MENU_ID = "MENU_ID";
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
    * 菜单ID
    */
    @TableField("menu_id")
    private Long menuId;
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

