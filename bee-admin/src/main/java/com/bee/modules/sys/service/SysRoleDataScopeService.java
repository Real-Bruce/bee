package com.bee.modules.sys.service;

import com.bee.common.service.BaseService;
import com.bee.modules.sys.entity.SysRoleDataScope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleDataScopeService
 */
@Service
public interface SysRoleDataScopeService extends BaseService<SysRoleDataScope> {

    List<Long> listDeptIds(Long roleId);

    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    void deleteByRoleIds(Long[] roleIds);
}
