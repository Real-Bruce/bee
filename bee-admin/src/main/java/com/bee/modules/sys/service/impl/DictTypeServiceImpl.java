package com.bee.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.sys.dto.DictTypeDTO;
import com.bee.modules.sys.entity.DictData;
import com.bee.modules.sys.entity.DictType;
import com.bee.modules.sys.mapper.DictDataMapper;
import com.bee.modules.sys.mapper.DictTypeMapper;
import com.bee.modules.sys.service.DictTypeService;
import com.bee.modules.sys.vo.DictDataVO;
import com.bee.modules.sys.vo.DictTypeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/09
 * @desc DictTypeServiceImpl
 */
@Service
@AllArgsConstructor
public class DictTypeServiceImpl extends BaseServiceImpl<DictTypeMapper, DictType> implements DictTypeService {
    private final DictDataMapper dictDataMapper;

    @Override
    public PageData<DictTypeDTO> page(Map<String, Object> params) {
        IPage<DictType> page = baseMapper.selectPage(
                getPage(params, "sort", true),
                getWrapper(params));
        return getPageData(page, DictTypeDTO.class);
    }

    private Wrapper<DictType> getWrapper(Map<String, Object> params) {
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");

        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(dictType), DictType.DICT_TYPE, dictType);
        queryWrapper.like(StrUtil.isNotBlank(dictName), DictType.DICT_NAME, dictName);
        return queryWrapper;
    }

    @Override
    public DictTypeDTO get(Long id) {
        DictType dictType = baseMapper.selectById(id);
        return ConvertUtils.sourceToTarget(dictType, DictTypeDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DictTypeDTO dto) {
        DictType dictType = ConvertUtils.sourceToTarget(dto, DictType.class);
        insert(dictType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictTypeDTO dto) {
        DictType dictType = ConvertUtils.sourceToTarget(dto, DictType.class);
        updateById(dictType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        deleteBachIds(Arrays.asList(ids));
    }

    @Override
    public List<DictTypeVO> allList() {
        List<DictTypeVO> dictTypeList = baseMapper.allList();
        List<DictDataVO> dictDataList = dictDataMapper.allList();

        for (DictTypeVO dictType : dictTypeList) {
            for (DictDataVO dictData : dictDataList) {
                if (dictType.getId().equals(dictData.getDictTypeId())) {
                    dictType.getDataList().add(dictData);
                }
            }
        }
        return dictTypeList;
    }
}
