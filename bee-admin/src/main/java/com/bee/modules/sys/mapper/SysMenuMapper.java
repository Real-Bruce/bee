package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysMenuMapper
 */
@Mapper
public interface SysMenuMapper extends BMapper<SysMenu> {
    SysMenu getById(@Param("id") Long id);

    /**
     * 获取菜单列表
     * @param menuType 菜单类型
     */
    List<SysMenu> listByMenuType(@Param("menuType") Integer menuType);

    /**
     * 获取菜单列表
     * @param userId 用户ID
     * @param menuType 菜单类型
     */
    List<SysMenu> listByUserId(@Param("userId") Long userId, @Param("menuType") Integer menuType);

    /**
     * 获取用户权限列表
     * @param userId 用户ID
     */
    List<String> listUserPermissions(@Param("userId") Long userId);

    /**
     * 获取全部权限列表
     */
    List<String> listPermissions();

    /**
     * 根据父级菜单查询子菜单
     * @param pid 父级菜单ID
     */
    List<SysMenu> listByPid(@Param("pid") Long pid);

}

