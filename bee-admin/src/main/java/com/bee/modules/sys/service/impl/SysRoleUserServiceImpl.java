package com.bee.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysRoleUser;
import com.bee.modules.sys.mapper.SysRoleUserMapper;
import com.bee.modules.sys.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysRoleUserServiceImpl
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIds) {
        // 新增修改前先删除角色用户关系
        deleteByUserIds(new Long[] {userId});

        // 用户没有角色绑定时直接返回
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }

        // 保存角色用户关系
        for (Long roleId : roleIds) {
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(userId);
            roleUser.setRoleId(roleId);
            insert(roleUser);
        }

    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseMapper.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseMapper.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> listRoleIdsByUserId(Long userId) {
        return baseMapper.listRoleIdsByUserId(userId);
    }
}
