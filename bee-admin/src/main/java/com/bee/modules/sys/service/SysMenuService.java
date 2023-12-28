package com.bee.modules.sys.service;

import com.bee.common.service.BaseService;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dto.SysMenuDTO;
import com.bee.modules.sys.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysMenuService
 */
@Service
public interface SysMenuService extends BaseService<SysMenu> {

    SysMenuDTO getById(Long id);

    void save(SysMenuDTO dto);

    void update(SysMenuDTO dto);

    void delete(Long id);

    List<SysMenuDTO> listByType(Integer menuType);

    List<SysMenuDTO> listByUser(UserDetail user, Integer menuType);

    List<SysMenuDTO> listByPid(Long pid);


}
