package com.bee.modules.sys.service.impl;

import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.sys.entity.SysMenu;
import com.bee.modules.sys.mapper.SysMenuMapper;
import com.bee.modules.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysMenuServiceImpl
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;

    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }
}
