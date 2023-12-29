package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleMapper
 */
@Mapper
public interface SysRoleMapper extends BMapper<SysRole> {

}

