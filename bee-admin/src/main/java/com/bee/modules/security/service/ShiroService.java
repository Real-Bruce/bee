package com.bee.modules.security.service;

import com.bee.modules.security.entity.SysUserToken;
import com.bee.modules.security.user.UserDetail;

import java.util.List;
import java.util.Set;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description shiro相关接口
 */
public interface ShiroService {

    /**
     * 获取用户权限信息
     */
    Set<String> getUserPermissions(UserDetail userDetail);

    SysUserToken getByToken(String token);

    /**
     * 根据userId，获取用户
     */
    SysUserToken getUser(Long userId);

    /**
     * 获取用户的部门权限
     * @param userId
     * @return
     */
    List<Long> getDataScopeList(Long userId);
}
