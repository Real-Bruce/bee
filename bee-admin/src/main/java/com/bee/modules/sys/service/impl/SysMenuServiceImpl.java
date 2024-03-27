package com.bee.modules.sys.service.impl;

import com.bee.common.constant.Constant;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.common.TreeUtils;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.bee.modules.sys.dto.SysMenuDTO;
import com.bee.modules.sys.entity.SysMenu;
import com.bee.modules.sys.mapper.SysMenuMapper;
import com.bee.modules.sys.service.SysMenuService;
import com.bee.modules.sys.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysMenuServiceImpl
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public SysMenuDTO getById(Long id) {
        SysMenu sysMenu = baseMapper.getById(id);
        return ConvertUtils.sourceToTarget(sysMenu, SysMenuDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysMenuDTO dto) {
        SysMenu sysMenu = ConvertUtils.sourceToTarget(dto, SysMenu.class);
        insert(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuDTO dto) {
        SysMenu sysMenu = ConvertUtils.sourceToTarget(dto, SysMenu.class);

        if (sysMenu.getId().equals(sysMenu.getPid())) {
            throw new BeeException(ErrorCode.SUPERIOR_MENU_ERROR);
        }

        updateById(sysMenu);
    }

    @Override
    public void delete(Long id) {
        // 删除菜单
        deleteById(id);

        // 删除角色菜单关系
        sysRoleMenuService.deleteByMenuId(id);
    }

    @Override
    public List<SysMenuDTO> listByType(Integer menuType) {
        List<SysMenu> menuList = baseMapper.listByMenuType(menuType);
        List<SysMenuDTO> menuDTOS = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
        return TreeUtils.build(menuDTOS, Constant.MENU_ROOT);
    }

    @Override
    public List<SysMenuDTO> listByUser(UserDetail user, Integer menuType) {
        List<SysMenu> menuList;

        // 系统管理员最高权限
        if (SuperAdminEnum.YES.getValue() == user.getSuperAdmin()) {
            menuList = baseMapper.listByMenuType(menuType);
        } else {
            menuList = baseMapper.listByUserId(user.getId(), menuType);
        }

        List<SysMenuDTO> menuDTOS = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

        return TreeUtils.build(menuDTOS);
    }

    @Override
    public List<SysMenuDTO> listByPid(Long pid) {
        List<SysMenu> menuList = baseMapper.listByPid(pid);
        return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
    }
}
