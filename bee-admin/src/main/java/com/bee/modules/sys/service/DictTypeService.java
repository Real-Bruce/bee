package com.bee.modules.sys.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.DictTypeDTO;
import com.bee.modules.sys.entity.DictType;
import com.bee.modules.sys.vo.DictTypeVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/09
 * @desc DictTypeService
 */
public interface DictTypeService extends BaseService<DictType> {

    PageData<DictTypeDTO> page(Map<String, Object> params);

    DictTypeDTO get(Long id);

    void save(DictTypeDTO dto);

    void update(DictTypeDTO dto);

    void delete(Long[] ids);

    List<DictTypeVO> allList();

}
