package com.bee.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.modules.oss.entity.Oss;
import com.bee.modules.oss.mapper.OssMapper;
import com.bee.modules.oss.service.OssService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc OssServiceImpl
 */
@Service
@AllArgsConstructor
public class OssServiceImpl extends BaseServiceImpl<OssMapper, Oss> implements OssService {
    private final OssMapper ossMapper;

    @Override
    public PageData<Oss> page(Map<String, Object> params) {
        IPage<Oss> page = baseMapper.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                new QueryWrapper<>()
        );

        return getPageData(page, Oss.class);
    }
}
