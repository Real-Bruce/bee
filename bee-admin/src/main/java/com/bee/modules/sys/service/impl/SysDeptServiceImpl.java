package com.bee.modules.sys.service.impl;

import com.bee.common.constant.Constant;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.common.TreeUtils;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.bee.modules.sys.dto.SysDeptDTO;
import com.bee.modules.sys.entity.SysDept;
import com.bee.modules.sys.mapper.SysDeptMapper;
import com.bee.modules.sys.mapper.SysUserMapper;
import com.bee.modules.sys.service.SysDeptService;
import com.qiniu.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDeptServiceImpl
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    private final SysUserMapper sysUserMapper;

    @Override
    public List<SysDeptDTO> list(Map<String, Object> params) {
        UserDetail user = SecurityUser.getUser();
        // 普通管理员，仅能查询所属部门及子部门数据
        if (SuperAdminEnum.NO.getValue() == user.getSuperAdmin()) {
            params.put("deptIds", listSubDeptIdsById(user.getDeptId()));
        }

        // 查询部门列表
        List<SysDept> deptList = baseMapper.getList(params);
        List<SysDeptDTO> deptDTOList = ConvertUtils.sourceToTarget(deptList, SysDeptDTO.class);

        return TreeUtils.build(deptDTOList);
    }

    @Override
    public SysDeptDTO getById(Long id) {
        // super admin deptId is null
        if (Objects.isNull(id)) {
            return null;
        }

        SysDept sysDept = baseMapper.getById(id);
        return ConvertUtils.sourceToTarget(sysDept, SysDeptDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDeptDTO dto) {
        SysDept sysDept = ConvertUtils.sourceToTarget(dto, SysDept.class);

        sysDept.setPids(getPidList(sysDept.getPid()));
        insert(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDeptDTO dto) {
        SysDept sysDept = ConvertUtils.sourceToTarget(dto, SysDept.class);

        if (sysDept.getId().equals(sysDept.getPid())) {
            throw new BeeException(ErrorCode.SUPERIOR_DEPT_ERROR);
        }

        // 上级部门不能为自己
        List<Long> subDeptIdList = listSubDeptIdsById(sysDept.getId());
        if (subDeptIdList.contains(sysDept.getPid())) {
            throw new BeeException(ErrorCode.SUPERIOR_DEPT_ERROR);
        }

        // 上级部门不能为下级部门
        sysDept.setPids(getPidList(sysDept.getPid()));
        updateById(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // 判断是否有子部门
        List<Long> subDeptIdList = listSubDeptIdsById(id);
        if (subDeptIdList.size() > 1) {
            throw new BeeException(ErrorCode.DEPT_SUB_DELETE_ERROR);
        }

        // 判断部门下面是否有用户
        int count = sysUserMapper.countByDeptId(id);
        if (count > 0) {
            throw new BeeException(ErrorCode.DEPT_SUB_DELETE_ERROR);
        }

        baseMapper.deleteById(id);

    }

    @Override
    public List<Long> listSubDeptIdsById(Long id) {
        List<Long> deptIdList = baseMapper.listSubDeptById("%" + id + "%");
        deptIdList.add(id);

        return deptIdList;
    }

    /**
     * 获取所有的上级部门
     * @param pid 上级部门ID
     * @return String
     */
    private String getPidList(Long pid) {
        // root dept pid is null
        if (Constant.DEPT_ROOT.equals(pid)) {
            return Constant.DEPT_ROOT + "";
        }

        // get all dept id and pid list
        List<SysDept> deptList = baseMapper.listIdAndPid();

        // list to map
        Map<Long, SysDept> deptMap = deptList.stream().collect(Collectors.toMap(SysDept::getId, Function.identity()));

        // recursion get parent dept id list
        List<Long> pidList = new ArrayList<>();
        getPidTree(pid, deptMap, pidList);

        return StringUtils.join(pidList, ",");
    }

    private void getPidTree(Long pid, Map<Long, SysDept> deptMap, List<Long> pidList) {

        if (Constant.DEPT_ROOT.equals(pid)) {
            return;
        }

        SysDept parent = deptMap.get(pid);
        if (Objects.nonNull(parent)) {
            getPidTree(parent.getPid(), deptMap, pidList);
        }

        pidList.add(pid);

    }


}
