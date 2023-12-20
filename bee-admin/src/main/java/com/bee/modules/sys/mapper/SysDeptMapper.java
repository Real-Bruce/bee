package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDeptMapper
 */
@Mapper
public interface SysDeptMapper extends BMapper<SysDept> {

    List<SysDept> getList (Map<String, Object> params);

    SysDept getById(@Param("id") Long id);

    /**
     * 获取所有部门的id，pid列表
     */
    List<SysDept> listIdAndPid();

    /**
     * 根据id，获取所有子部门ID列表
     * @param id 部门ID
     */
    List<Long> listSubDeptById(@Param("id") String id);
}

