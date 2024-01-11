package com.bee.modules.oss.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.oss.entity.Oss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc OssMapper
 */
@Mapper
public interface OssMapper extends BMapper<Oss> {

}

