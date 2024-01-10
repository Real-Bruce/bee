package com.bee.modules.sys.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.DictDataDTO;
import com.bee.modules.sys.dto.DictTypeDTO;
import com.bee.modules.sys.entity.DictData;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc DictDataService
 */
public interface DictDataService extends BaseService<DictData> {

    PageData<DictDataDTO> page(Map<String, Object> params);

    DictDataDTO get(Long id);

    void save(DictDataDTO dto);

    void update(DictDataDTO dto);

    void delete(Long[] ids);
}
