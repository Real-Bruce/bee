package com.bee.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.common.JsonUtils;
import com.bee.modules.sys.dto.SysParamsDTO;
import com.bee.modules.sys.entity.SysParams;
import com.bee.modules.sys.mapper.SysParamsMapper;
import com.bee.modules.sys.redis.SysParamsRedis;
import com.bee.modules.sys.service.SysParamsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysParamsServiceImpl
 */
@Service
@AllArgsConstructor
public class SysParamsServiceImpl extends BaseServiceImpl<SysParamsMapper, SysParams> implements SysParamsService {
    private final SysParamsRedis sysParamsRedis;

    @Override
    public PageData<SysParamsDTO> page(Map<String, Object> params) {
        IPage<SysParams> page = baseMapper.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );

        return getPageData(page, SysParamsDTO.class);
    }

    @Override
    public List<SysParamsDTO> list(Map<String, Object> params) {
        List<SysParams> paramsList = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(paramsList, SysParamsDTO.class);
    }

    @Override
    public SysParamsDTO get(Long id) {
        SysParams sysParams = baseMapper.selectById(id);
        return ConvertUtils.sourceToTarget(sysParams, SysParamsDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysParamsDTO dto) {
        SysParams sysParams = ConvertUtils.sourceToTarget(dto, SysParams.class);
        insert(sysParams);

        sysParamsRedis.set(sysParams.getParamCode(), sysParams.getParamValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysParamsDTO dto) {
        SysParams sysParams = ConvertUtils.sourceToTarget(dto, SysParams.class);
        updateById(sysParams);

        sysParamsRedis.set(sysParams.getParamCode(), sysParams.getParamValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        List<String> paramCodeList = baseMapper.listParamCodeByIds(ids);
        String[] paramCodes = paramCodeList.toArray(new String[paramCodeList.size()]);
        sysParamsRedis.delete(paramCodes);

        deleteBachIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String paramCode) {
        String paramValue = sysParamsRedis.get(paramCode);
        if (Objects.isNull(paramValue)) {
            paramValue = baseMapper.getValueByCode(paramCode);

            sysParamsRedis.set(paramCode, paramValue);
        }

        return paramValue;
    }

    @Override
    public <T> T getValueObject(String paramCode, Class<T> clazz) {
        String paramValue = getValue(paramCode);
        if (StrUtil.isNotBlank(paramValue)) {
            return JsonUtils.parseObject(paramValue, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeeException(ErrorCode.PARAMS_GET_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateValueByCode(String paramCode, String paramValue) {
        int count = baseMapper.updateValueByCode(paramCode, paramValue);
        sysParamsRedis.set(paramCode, paramValue);
        return count;
    }

    private QueryWrapper<SysParams> getWrapper(Map<String, Object> params) {
        String paramCode = (String) params.get("paramCode");

        QueryWrapper<SysParams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_type", 1);
        queryWrapper.like(StrUtil.isNotBlank(paramCode), "param_code", paramCode);

        return queryWrapper;
    }
}
