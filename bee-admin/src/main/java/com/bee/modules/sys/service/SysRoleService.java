package com.bee.modules.sys.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.SysRoleDTO;
import com.bee.modules.sys.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleService
 */
@Service
public interface SysRoleService extends BaseService<SysRole> {

    PageData<SysRoleDTO> page(Map<String, Object> params);

    List<SysRoleDTO> list(Map<String, Object> params);

    SysRoleDTO get(Long id);

    void saveOrUpdate(SysRoleDTO dto);

    void delete(Long[] ids);
}
