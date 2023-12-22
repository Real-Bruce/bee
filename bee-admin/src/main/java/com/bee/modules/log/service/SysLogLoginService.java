package com.bee.modules.log.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.log.dto.SysLogLoginDTO;
import com.bee.modules.log.entity.SysLogLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogLoginService
 */
@Service
public interface SysLogLoginService extends BaseService<SysLogLogin> {
    PageData<SysLogLoginDTO> page (Map<String, Object> params);

    List<SysLogLoginDTO> list (Map<String, Object> params);
    void save(SysLogLogin logLogin);

}
