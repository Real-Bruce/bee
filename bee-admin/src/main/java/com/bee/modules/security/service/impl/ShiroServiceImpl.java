package com.bee.modules.security.service.impl;

import com.bee.modules.security.entity.SysUserToken;
import com.bee.modules.security.service.ShiroService;
import com.bee.modules.security.user.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    @Override
    public Set<String> getUserPermissions(UserDetail userDetail) {
        return null;
    }

    @Override
    public SysUserToken getByToken(String token) {
        return null;
    }

    @Override
    public SysUserToken getUser(Long userId) {
        return null;
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return null;
    }
}
