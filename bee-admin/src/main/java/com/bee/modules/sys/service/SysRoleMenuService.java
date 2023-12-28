package com.bee.modules.sys.service;

import com.bee.common.service.BaseService;
import com.bee.modules.sys.entity.SysRoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/28
 * @desc SysRoleMenuService
 */
@Service
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

    List<Long> listMenuIdByRoleId(Long roleId);

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    void deleteByRoleIds(Long[] roleIds);

    void deleteByMenuId(Long menuId);
}
