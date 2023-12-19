package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserMapper
 */
@Mapper
public interface SysUserMapper extends BMapper<SysUser> {

    List<SysUser> listByParams(Map<String, Object> params);

    SysUser getById(@Param("id")Long id);

    SysUser getByUsername(@Param("username") String username);

    int updatePassword (@Param("id") Long id, @Param("newPassword") String newPassword);

    /**
     * 根据部门ID查询用户数量
     * @param deptId 部门ID
     */
    int getCountByDeptId(@Param("deptId") Long deptId);

    /**
     * 根据部门ID获取UserID
     * @param deptIds deptId list
     */
    List<Long> listUserIdsByDeptId(@Param("deptIdList") List<Long> deptIds);

}

