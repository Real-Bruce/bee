package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.exception.ErrorCode;
import com.bee.common.page.PageData;
import com.bee.common.util.ExcelUtils;
import com.bee.common.util.ResultVO;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.bee.modules.security.password.PasswordUtils;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dto.PasswordDTO;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.excel.SysUserExcel;
import com.bee.modules.sys.service.SysRoleUserService;
import com.bee.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserController
 */
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Api(tags = "用户管理")
public class SysUserController {
    private final SysUserService sysUserService;
    private final SysRoleUserService sysRoleUserService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("sys:user:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "性别", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门ID", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<SysUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> param) {
        PageData<SysUserDTO> page = sysUserService.page(param);
        return new ResultVO<PageData<SysUserDTO>>().ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("ID查询")
    @RequiresPermissions("sys:user:info")
    public ResultVO<SysUserDTO> get(@PathVariable("id") Long id) {
        SysUserDTO userDTO = sysUserService.getById(id);

        // 查询角色信息
        List<Long> roleIdList = sysRoleUserService.listRoleIdsByUserId(id);
        userDTO.setRoleIdList(roleIdList);

        return new ResultVO<SysUserDTO>().ok(userDTO);
    }

    @GetMapping("info")
    @ApiOperation("当前登录用户信息")
    public ResultVO<SysUserDTO> info() {
        SysUserDTO sysUserDTO = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
        return new ResultVO<SysUserDTO>().ok(sysUserDTO);
    }

    @PutMapping("/password")
    @ApiOperation("修改密码")
    @LogOperation("修改密码")
    public ResultVO password(@RequestBody PasswordDTO dto) {

        ValidatorUtils.validateEntity(dto);

        UserDetail user = SecurityUser.getUser();

        if (PasswordUtils.matchs(dto.getPassword(), user.getPassword())) {
            return new ResultVO().error(ErrorCode.PASSWORD_ERROR);
        }

        sysUserService.updatePassword(user.getUserId(), dto.getNewPassword());
        return new ResultVO();
    }

    @PostMapping
    @ApiOperation("新增用户")
    @LogOperation("新增用户")
    @RequiresPermissions("sys:user:save")
    public ResultVO save(@RequestBody SysUserDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysUserService.save(dto);
        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("修改用户")
    @LogOperation("修改用户")
    @RequiresPermissions("sys:user:update")
    public ResultVO update(@RequestBody SysUserDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysUserService.update(dto);
        return new ResultVO();
    }

    @DeleteMapping
    @ApiOperation("删除用户")
    @LogOperation("删除用户")
    @RequiresPermissions("sys:user:delete")
    public ResultVO delete(@RequestBody Long[] ids) {

        AssertUtils.isArrayEmpty(ids, "id");

        sysUserService.deleteBachIds(Arrays.asList(ids));
        return new ResultVO();
    }

    @GetMapping("/export")
    @ApiOperation("报表导出")
    @LogOperation("报表导出")
    @RequiresPermissions("sys:suer:export")
    @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysUserDTO> list = sysUserService.list(params);

        ExcelUtils.exportExcelToTarget(response, "用户管理", list, SysUserExcel.class);
    }

}

