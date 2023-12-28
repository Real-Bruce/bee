package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.SysParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysParamsMapper
 */
@Mapper
public interface SysParamsMapper extends BMapper<SysParams> {

    String getValueByCode(@Param("paramCode") String paramCode);

    List<String> listParamCodeByIds(@Param("ids") Long[] ids);

    int updateValueByCode(@Param("paramCode") String paramCode, @Param("paramValue") String paramValue);
}

