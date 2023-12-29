package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.bee.modules.sys.dto.SysRoleDTO;
import com.bee.modules.sys.service.SysRoleDataScopeService;
import com.bee.modules.sys.service.SysRoleMenuService;
import com.bee.modules.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysRoleController
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理")
@AllArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("sys:role:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "角色名", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<SysRoleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysRoleDTO> page = sysRoleService.page(params);

        return new ResultVO<PageData<SysRoleDTO>>().ok(page);
    }

    @GetMapping("/list")
    @ApiOperation("列表查询")
    @RequiresPermissions("sys:role:list")
    public ResultVO<List<SysRoleDTO>> list() {
        List<SysRoleDTO> roleDTOS = sysRoleService.list(new HashMap<>(1));
        return new ResultVO<List<SysRoleDTO>>().ok(roleDTOS);
    }

    @GetMapping("/{id}")
    @ApiOperation("ID查询")
    @RequiresPermissions("sys:role:info")
    public ResultVO<SysRoleDTO> get(@PathVariable("id") Long id) {
        SysRoleDTO roleDTO = sysRoleService.get(id);

        List<Long> menuIdList = sysRoleMenuService.listMenuIdByRoleId(id);
        roleDTO.setMenuIdList(menuIdList);

        List<Long> deptIds = sysRoleDataScopeService.listDeptIds(id);
        roleDTO.setDeptIdList(deptIds);

        return new ResultVO<SysRoleDTO>().ok(roleDTO);
    }

    @PostMapping
    @ApiOperation("角色保存")
    @LogOperation("角色保存")
    @RequiresPermissions("sys:role:save")
    public ResultVO save(@RequestBody SysRoleDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysRoleService.saveOrUpdate(dto);
        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("角色更新")
    @LogOperation("角色更新")
    @RequiresPermissions("sys:role:update")
    public ResultVO update(@RequestBody SysRoleDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysRoleService.saveOrUpdate(dto);
        return new ResultVO();
    }

    @DeleteMapping
    @ApiOperation("删除角色")
    @LogOperation("删除角色")
    @RequiresPermissions("sys:role:delete")
    public ResultVO delete(@RequestBody Long[] ids) {
        AssertUtils.isArrayEmpty(ids, "id");
        sysRoleService.delete(ids);
        return new ResultVO();
    }
}

