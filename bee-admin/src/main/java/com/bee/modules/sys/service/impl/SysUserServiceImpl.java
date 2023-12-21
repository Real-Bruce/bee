package com.bee.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.security.password.PasswordUtils;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.entity.SysUser;
import com.bee.modules.sys.mapper.SysUserMapper;
import com.bee.modules.sys.service.SysDeptService;
import com.bee.modules.sys.service.SysRoleUserService;
import com.bee.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserServiceImpl
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleUserService sysRoleUserService;
    private final SysDeptService sysDeptService;

    @Override
    public PageData<SysUserDTO> page(Map<String, Object> params) {
        paramsToLike(params, "username");

        IPage<SysUser> page = getPage(params, Constant.CREATE_DATE, false);

        // 普通管理员仅查询所属部门及子部门
        UserDetail user = SecurityUser.getUser();
        if (SuperAdminEnum.NO.getValue() == user.getSupperAdmin()) {
            params.put("deptIdList", sysDeptService.listSubDeptIdsById(user.getDeptId()));
        }

        List<SysUser> userList = baseMapper.listByParams(params);
        return getPageData(userList, page.getTotal(), SysUserDTO.class);
    }

    @Override
    public List<SysUserDTO> list(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        if (SuperAdminEnum.NO.getValue() == user.getSupperAdmin()) {
            params.put("deptIds", sysDeptService.listSubDeptIdsById(user.getDeptId()));
        }

        List<SysUser> userList = baseMapper.listByParams(params);
        return ConvertUtils.sourceToTarget(userList, SysUserDTO.class);
    }

    @Override
    public SysUserDTO getById(Long id) {
        SysUser sysUser = baseMapper.getById(id);
        return ConvertUtils.sourceToTarget(sysUser, SysUserDTO.class);
    }

    @Override
    public SysUserDTO getByUsername(String username) {
        SysUser sysUser = baseMapper.getByUsername(username);
        return ConvertUtils.sourceToTarget(sysUser, SysUserDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDTO dto) {
        SysUser sysUser = ConvertUtils.sourceToTarget(dto, SysUser.class);

        // 密码加密
        String password = PasswordUtils.encode(sysUser.getPassword());
        sysUser.setPassword(password);
        sysUser.setSuperAdmin(SuperAdminEnum.NO.getValue());

        insert(sysUser);
        // 保存角色关系
        sysRoleUserService.saveOrUpdate(sysUser.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserDTO dto) {
        SysUser sysUser = ConvertUtils.sourceToTarget(dto, SysUser.class);

        // 密码加密
        if (StrUtil.isBlank(dto.getPassword())) {
            sysUser.setPassword(null);
        } else {
            String password = PasswordUtils.encode(sysUser.getPassword());
            sysUser.setPassword(password);
        }

        updateById(sysUser);
        // 更新角色用户关系
        sysRoleUserService.saveOrUpdate(sysUser.getId(), dto.getRoleIdList());

    }

    @Override
    public void delete(Long[] ids) {
        baseMapper.deleteBatchIds(Arrays.asList(ids));
        sysRoleUserService.deleteByUserIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);
        baseMapper.updatePassword(id, newPassword);
    }

    @Override
    public int countByDeptId(Long deptId) {
        return baseMapper.countByDeptId(deptId);
    }

    @Override
    public List<Long> listUserIdsByDeptId(List<Long> deptIds) {
        return baseMapper.listUserIdsByDeptId(deptIds);
    }
}
