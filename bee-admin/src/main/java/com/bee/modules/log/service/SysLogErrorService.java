package com.bee.modules.log.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.log.dto.SysLogErrorDTO;
import com.bee.modules.log.entity.SysLogError;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogErrorService
 */
public interface SysLogErrorService extends BaseService<SysLogError> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogError logError);
}
