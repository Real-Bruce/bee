package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysRoleDataScope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleDataScopeMapper
 */
@Mapper
public interface SysRoleDataScopeMapper extends BMapper<SysRoleDataScope> {

    /**
     * 根据角色ID，查询部门ID列表
     * @param roleId
     * @return DeptId list
     */
    List<Long> listDeptIdByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取用户的部门权限列表
     * @param userId 用户ID
     * @return DataScope list
     */
    List<Long> listDataScopeByUserId(@Param("userId") Long userId);

    /**
     * 根据角色Id，删除角色数据权限关系
     * @param roleIds 角色ID数组
     */
    void deleteByRoleIds(@Param("roleIds") Long[] roleIds);
}

