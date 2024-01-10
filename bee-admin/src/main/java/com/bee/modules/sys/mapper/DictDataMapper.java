package com.bee.modules.sys.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.sys.entity.DictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.modules.sys.vo.DictDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc DictDataMapper
 */
@Mapper
public interface DictDataMapper extends BMapper<DictData> {

    List<DictDataVO> allList();
}

