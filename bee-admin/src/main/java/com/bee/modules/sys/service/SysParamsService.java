package com.bee.modules.sys.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.SysParamsDTO;
import com.bee.modules.sys.entity.SysParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysParamsService
 */
public interface SysParamsService extends BaseService<SysParams> {

    PageData<SysParamsDTO> page(Map<String, Object> params);

    List<SysParamsDTO> list(Map<String, Object> params);

    SysParamsDTO get(Long id);

    void save(SysParamsDTO dto);

    void update(SysParamsDTO dto);

    void delete(Long[] ids);

    String getValue(String paramCode);

    /**
     * 根据参数编码获取value的Object对象
     * @param paramCode 参数编码
     * @param clazz Object对象
     */
    <T> T getValueObject(String paramCode, Class<T> clazz);

    int updateValueByCode(String paramCode, String paramValue);
}
