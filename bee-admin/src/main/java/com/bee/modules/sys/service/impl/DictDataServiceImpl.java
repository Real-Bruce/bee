package com.bee.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.sys.dto.DictDataDTO;
import com.bee.modules.sys.entity.DictData;
import com.bee.modules.sys.mapper.DictDataMapper;
import com.bee.modules.sys.service.DictDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc DictDataServiceImpl
 */
@Service

public class DictDataServiceImpl extends BaseServiceImpl<DictDataMapper, DictData> implements DictDataService {
    @Override
    public PageData<DictDataDTO> page(Map<String, Object> params) {
        IPage<DictData> page = baseMapper.selectPage(
                getPage(params, DictData.SORT, true),
                getWrapper(params)
        );

        return getPageData(page, DictDataDTO.class);
    }

    private Wrapper<DictData> getWrapper(Map<String, Object> params) {
        String dictTypeId = (String) params.get("dictTypeId");
        String dictLabel = (String) params.get("dictLabel");
        String dictValue = (String) params.get("dictValue");

        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DictData.DICT_TYPE_ID, dictTypeId);
        queryWrapper.eq(StrUtil.isNotBlank(dictLabel), DictData.DICT_LABEL, dictLabel);
        queryWrapper.eq(StrUtil.isNotBlank(dictValue), DictData.DICT_VALUE, dictValue);

        return queryWrapper;
    }

    @Override
    public DictDataDTO get(Long id) {
        return ConvertUtils.sourceToTarget(baseMapper.selectById(id), DictDataDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DictDataDTO dto) {
        DictData dictData = ConvertUtils.sourceToTarget(dto, DictData.class);
        insert(dictData);
    }

    @Override
    public void update(DictDataDTO dto) {
        DictData dictData = ConvertUtils.sourceToTarget(dto, DictData.class);
        updateById(dictData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        deleteBachIds(Arrays.asList(ids));
    }
}
