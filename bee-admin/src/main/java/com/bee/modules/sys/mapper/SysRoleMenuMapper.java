package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/28
 * @desc SysRoleMenuMapper
 */
@Mapper
public interface SysRoleMenuMapper extends BMapper<SysRoleMenu> {

    List<Long> listMenuIdByRoleId(@Param("roleId") Long roleId);

    void deleteByRoleIds(@Param("roleIds") Long[] roleIds);

    void deleteByMenuId(@Param("menuId") Long menuId);
}

