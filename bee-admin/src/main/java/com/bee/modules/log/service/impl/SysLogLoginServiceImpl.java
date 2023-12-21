package com.bee.modules.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.log.dto.SysLogLoginDTO;
import com.bee.modules.log.entity.SysLogLogin;
import com.bee.modules.log.mapper.SysLogLoginMapper;
import com.bee.modules.log.service.SysLogLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogLoginServiceImpl
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

    @Override
    public PageData<SysLogLoginDTO> page(Map<String, Object> params) {
        IPage<SysLogLogin> page = getPage(params, Constant.CREATE_DATE, false);
        QueryWrapper<SysLogLogin> queryWrapper = getWrapper(params);
        return getPageData(baseMapper.selectPage(page, queryWrapper), SysLogLoginDTO.class);
    }

    @Override
    public List<SysLogLoginDTO> list(Map<String, Object> params) {
        List<SysLogLogin> sysLogLogins = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(sysLogLogins, SysLogLoginDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogLoginDTO dto) {
        insert(ConvertUtils.sourceToTarget(dto, SysLogLogin.class));
    }

    private QueryWrapper<SysLogLogin> getWrapper(Map<String, Object> params) {
        String status = (String) params.get("status");
        String creatorName = (String) params.get("creatorName");

        QueryWrapper<SysLogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(status), SysLogLogin.STATUS, status);
        queryWrapper.eq(StrUtil.isNotBlank(creatorName), SysLogLogin.CREATOR_NAME, creatorName);
        return queryWrapper;
    }
}
