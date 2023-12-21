package com.bee.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.log.dto.SysLogErrorDTO;
import com.bee.modules.log.entity.SysLogError;
import com.bee.modules.log.mapper.SysLogErrorMapper;
import com.bee.modules.log.service.SysLogErrorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogErrorServiceImpl
 */
@Service
@AllArgsConstructor
public class SysLogErrorServiceImpl extends BaseServiceImpl<SysLogErrorMapper, SysLogError> implements SysLogErrorService {
    private final SysLogErrorMapper sysLogErrorMapper;

    @Override
    public PageData<SysLogErrorDTO> page(Map<String, Object> params) {
        IPage<SysLogError> page = baseMapper.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );

        return getPageData(page, SysLogErrorDTO.class);
    }

    @Override
    public List<SysLogErrorDTO> list(Map<String, Object> params) {
        List<SysLogError> logErrorList = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(logErrorList, SysLogErrorDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogError logError) {
        insert(logError);
    }

    private QueryWrapper<SysLogError> getWrapper(Map<String, Object> params){
        QueryWrapper<SysLogError> wrapper = new QueryWrapper<>();
        return wrapper;
    }
}
