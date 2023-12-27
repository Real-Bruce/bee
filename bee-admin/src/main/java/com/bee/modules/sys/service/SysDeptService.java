package com.bee.modules.sys.service;

import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.SysDeptDTO;
import com.bee.modules.sys.entity.SysDept;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDeptService
 */
@Service
public interface SysDeptService extends BaseService<SysDept> {

    List<SysDeptDTO> list(Map<String, Object> params);

    SysDeptDTO getById(Long id);

    void save(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    /**
     * 获取本部门以及所有子部门ID
     * @param id 本部门ID
     * @return 本部门以及所有子部门ID
     */
    List<Long> listSubDeptIdsById(Long id);
}
