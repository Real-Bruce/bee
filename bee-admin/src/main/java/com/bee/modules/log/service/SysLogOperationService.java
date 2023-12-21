package com.bee.modules.log.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.log.dto.SysLogOperationDTO;
import com.bee.modules.log.entity.SysLogOperation;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogOperationService
 */
public interface SysLogOperationService extends BaseService<SysLogOperation> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperation logOperation);
}
