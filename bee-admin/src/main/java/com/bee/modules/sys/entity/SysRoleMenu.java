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
 * @create 2023/12/28
 * @desc SysRoleMenu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu extends BaseEntity implements Serializable {
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

}

