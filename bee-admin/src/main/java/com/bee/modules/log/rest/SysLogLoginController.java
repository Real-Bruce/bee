package com.bee.modules.log.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ExcelUtils;
import com.bee.common.util.ResultVO;
import com.bee.modules.log.dto.SysLogLoginDTO;
import com.bee.modules.log.excel.SysLogLoginExcel;
import com.bee.modules.log.service.SysLogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogLoginController
 */

@RestController
@RequestMapping("/sys/log/login")
@AllArgsConstructor
@Api("登录日志")
public class SysLogLoginController {
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("sys:log:login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态  0：失败    1：成功    2：账号已锁定", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "creatorName", value = "用户名", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<SysLogLoginDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysLogLoginDTO> page = sysLogLoginService.page(params);
        return new ResultVO<PageData<SysLogLoginDTO>>().ok(page);
    }


    @GetMapping("/export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:log:login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态  0：失败    1：成功    2：账号已锁定", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "creatorName", value = "用户名", paramType = "query", dataType = "String")
    })
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogLoginDTO> list = sysLogLoginService.list(params);

        ExcelUtils.exportExcel(response, null, "登录日志", list, SysLogLoginExcel.class);
    }
}

