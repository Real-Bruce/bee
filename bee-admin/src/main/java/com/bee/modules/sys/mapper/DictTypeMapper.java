package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.DictType;
import com.bee.modules.sys.vo.DictTypeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Bruce
 * @create 2024/01/09
 * @desc DictTypeMapper
 */
@Mapper
public interface DictTypeMapper extends BMapper<DictType> {

    List<DictTypeVO> allList();

}

