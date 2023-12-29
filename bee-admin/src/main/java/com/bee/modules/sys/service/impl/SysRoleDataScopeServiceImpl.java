package com.bee.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysRoleDataScope;
import com.bee.modules.sys.mapper.SysRoleDataScopeMapper;
import com.bee.modules.sys.service.SysRoleDataScopeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleDataScopeServiceImpl
 */
@Service
@AllArgsConstructor
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeMapper, SysRoleDataScope> implements SysRoleDataScopeService {

    @Override
    public List<Long> listDeptIds(Long roleId) {
        return baseMapper.listDeptIdByRoleId(roleId);
    }

    @Override
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        // 删除原角色数据权限关系
        deleteByRoleIds(new Long[]{roleId});

        // 角色没绑定权限
        if (CollUtil.isEmpty(deptIdList)) {
            return;
        }

        for (Long deptId : deptIdList) {
            SysRoleDataScope roleDataScope = new SysRoleDataScope();
            roleDataScope.setRoleId(roleId);
            roleDataScope.setDeptId(deptId);

            insert(roleDataScope);
        }

    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseMapper.deleteByRoleIds(roleIds);
    }
}
