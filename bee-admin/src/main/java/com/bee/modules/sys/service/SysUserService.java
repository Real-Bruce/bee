package com.bee.modules.sys.service;

import com.bee.common.page.PageData;
import com.bee.common.service.BaseService;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.entity.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserService
 */
@Service
public interface SysUserService extends BaseService<SysUser> {
    PageData<SysUserDTO> page (Map<String, Object> params);

    List<SysUserDTO> list(Map<String, Object> params);

    SysUserDTO getById(Long id);

    SysUserDTO getByUsername(String username);

    void save(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long[] ids);

    void updatePassword(Long id, String newPassword);

    /**
     * 根据部门id，获取用户数
     * @param deptId
     * @return
     */
    int countByDeptId(Long deptId);

    /**
     * 根据部门Id，查询用户Id列表
     * @param deptIds
     * @return
     */
    List<Long> listUserIdsByDeptId (List<Long> deptIds);
}
