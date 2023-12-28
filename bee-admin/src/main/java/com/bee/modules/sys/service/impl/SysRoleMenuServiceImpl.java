package com.bee.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysRoleMenu;
import com.bee.modules.sys.mapper.SysRoleMenuMapper;
import com.bee.modules.sys.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/28
 * @desc SysRoleMenuServiceImpl
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<Long> listMenuIdByRoleId(Long roleId) {
        return baseMapper.listMenuIdByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        // 删除原有角色菜单关系
        deleteByRoleIds(new Long[]{roleId});

        if (CollUtil.isEmpty(menuIdList)) {
            return;
        }

        // 保存角色菜单
        for (Long menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);

            insert(sysRoleMenu);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(Long[] roleIds) {
        baseMapper.deleteByRoleIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByMenuId(Long menuId) {
        baseMapper.deleteByMenuId(menuId);
    }
}
