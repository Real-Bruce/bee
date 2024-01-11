package com.bee.modules.oss.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.oss.entity.Oss;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc OssService
 */
@Service
public interface OssService extends BaseService<Oss> {

    PageData<Oss> page(Map<String, Object> params);
}
