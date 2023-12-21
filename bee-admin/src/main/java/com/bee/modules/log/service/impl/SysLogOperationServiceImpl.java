package com.bee.modules.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.log.dto.SysLogOperationDTO;
import com.bee.modules.log.entity.SysLogOperation;
import com.bee.modules.log.mapper.SysLogOperationMapper;
import com.bee.modules.log.service.SysLogOperationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogOperationServiceImpl
 */
@Service
@AllArgsConstructor
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationMapper, SysLogOperation> implements SysLogOperationService {
    private final SysLogOperationMapper sysLogOperationMapper;

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        QueryWrapper<SysLogOperation> queryWrapper = getWrapper(params);
        IPage<SysLogOperation> page = getPage(params, Constant.CREATE_DATE, false);

        return getPageData(baseMapper.selectPage(page, queryWrapper), SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<SysLogOperation> sysLogOperations = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(sysLogOperations, SysLogOperationDTO.class);
    }

    @Override
    public void save(SysLogOperation logOperation) {
        insert(logOperation);
    }

    private QueryWrapper<SysLogOperation> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");

        QueryWrapper<SysLogOperation> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);

        return wrapper;
    }
}
