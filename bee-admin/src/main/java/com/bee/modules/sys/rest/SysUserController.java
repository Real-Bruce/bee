package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ResultVO;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.service.SysRoleUserService;
import com.bee.modules.sys.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysUserController
 */
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
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

        return null;
    }
}

