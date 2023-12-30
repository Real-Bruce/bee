package com.bee.modules.sys.service;

import com.bee.common.service.BaseService;
import com.bee.modules.sys.entity.SysRoleUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysRoleUserService
 */
public interface SysRoleUserService extends BaseService<SysRoleUser> {

    void saveOrUpdate(Long userId, List<Long> roleIds);

    void deleteByRoleIds(Long[] roleIds);

    void deleteByUserIds(Long[] userIds);

    List<Long> listRoleIdsByUserId(Long userId);
}
