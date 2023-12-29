package com.bee.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.bee.modules.sys.dto.SysRoleDTO;
import com.bee.modules.sys.entity.SysRole;
import com.bee.modules.sys.mapper.SysRoleMapper;
import com.bee.modules.sys.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleServiceImpl
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;
    private final SysRoleUserService sysRoleUserService;
    private final SysDeptService sysDeptService;

    @Override
    public PageData<SysRoleDTO> page(Map<String, Object> params) {
        IPage<SysRole> roleIPage = baseMapper.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );
        return getPageData(roleIPage, SysRoleDTO.class);
    }

    @Override
    public List<SysRoleDTO> list(Map<String, Object> params) {
        List<SysRole> roleList = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(roleList, SysRoleDTO.class);
    }

    @Override
    public SysRoleDTO get(Long id) {
        SysRole sysRole = baseMapper.selectById(id);
        return ConvertUtils.sourceToTarget(sysRole, SysRoleDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(SysRoleDTO dto) {

        SysRole sysRole = ConvertUtils.sourceToTarget(dto, SysRole.class);
        if (Objects.isNull(dto.getId())) {
            // 保存角色信息
            updateById(sysRole);
        } else {
            // 更新角色信息
            insert(sysRole);
        }

        // 保存/更新角色菜单关系
        sysRoleMenuService.saveOrUpdate(sysRole.getId(), dto.getMenuIdList());
        // 保存/更新角色数据权限关系
        sysRoleDataScopeService.saveOrUpdate(sysRole.getId(), dto.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        // 删除角色
        baseMapper.deleteBatchIds(Arrays.asList(ids));

        // 删除角色用户关系
        sysRoleUserService.deleteByRoleIds(ids);

        // 删除角色菜单关系
        sysRoleMenuService.deleteByRoleIds(ids);

        // 删除角色数据权限关系
        sysRoleDataScopeService.deleteByRoleIds(ids);
    }

    private QueryWrapper<SysRole> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(name), "name", name);

        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSupperAdmin() == SuperAdminEnum.NO.getValue()) {
            List<Long> deptIdList = sysDeptService.listSubDeptIdsById(user.getDeptId());
            wrapper.in(deptIdList != null, "dept_id", deptIdList);
        }

        return wrapper;
    }
}
