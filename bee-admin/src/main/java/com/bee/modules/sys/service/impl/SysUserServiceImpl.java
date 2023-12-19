package com.bee.modules.sys.service.impl;

import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysUser;
import com.bee.modules.sys.mapper.SysUserMapper;
import com.bee.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserServiceImpl
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
}
