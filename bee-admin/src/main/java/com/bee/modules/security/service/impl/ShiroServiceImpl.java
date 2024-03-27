package com.bee.modules.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bee.modules.security.entity.SysUserToken;
import com.bee.modules.security.mapper.SysUserTokenMapper;
import com.bee.modules.security.service.ShiroService;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.bee.modules.sys.entity.SysUser;
import com.bee.modules.sys.mapper.SysMenuMapper;
import com.bee.modules.sys.mapper.SysRoleDataScopeMapper;
import com.bee.modules.sys.mapper.SysUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description
 */
@Service
@AllArgsConstructor
public class ShiroServiceImpl implements ShiroService {

    private final SysMenuMapper sysMenuMapper;
    private final SysUserMapper sysUserMapper;
    private final SysUserTokenMapper sysUserTokenMapper;
    private final SysRoleDataScopeMapper sysRoleDataScopeMapper;

    @Override
    public Set<String> getUserPermissions(UserDetail userDetail) {
        // system admin top permission
        List<String> permissionsList;
        if (SuperAdminEnum.YES.getValue() == userDetail.getSuperAdmin()) {
            permissionsList = sysMenuMapper.listPermissions();
        } else {
            permissionsList = sysMenuMapper.listUserPermissions(userDetail.getId());
        }

        // normal user permission
        HashSet<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StrUtil.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserToken getByToken(String token) {
        return sysUserTokenMapper.getByToken(token);
    }

    @Override
    public SysUser getUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeMapper.listDataScopeByUserId(userId);
    }
}
