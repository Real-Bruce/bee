package com.bee.modules.sys.service.impl;

import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysRoleDataScope;
import com.bee.modules.sys.mapper.SysRoleDataScopeMapper;
import com.bee.modules.sys.service.SysRoleDataScopeService;
import org.springframework.stereotype.Service;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleDataScopeServiceImpl
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeMapper, SysRoleDataScope> implements SysRoleDataScopeService {
    private final SysRoleDataScopeMapper sysRoleDataScopeMapper;

    public SysRoleDataScopeServiceImpl(SysRoleDataScopeMapper sysRoleDataScopeMapper) {
        this.sysRoleDataScopeMapper = sysRoleDataScopeMapper;
    }
}
