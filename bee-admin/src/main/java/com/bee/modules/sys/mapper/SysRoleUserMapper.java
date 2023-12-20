package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysRoleUserMapper
 */
@Mapper
public interface SysRoleUserMapper extends BMapper<SysRoleUser> {

    void deleteByRoleIds(@Param("roleIds") Long[] roleIds);

    void deleteByUserIds(@Param("userIds") Long[] userIds);

    List<Long> listRoleIdsByUserId(@Param("userId") Long userId);
}

