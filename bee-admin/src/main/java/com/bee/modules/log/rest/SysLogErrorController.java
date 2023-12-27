package com.bee.modules.log.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ExcelUtils;
import com.bee.common.util.ResultVO;
import com.bee.modules.log.dto.SysLogErrorDTO;
import com.bee.modules.log.entity.SysLogError;
import com.bee.modules.log.excel.SysLogErrorExcel;
import com.bee.modules.log.service.SysLogErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/21
 * @desc SysLogErrorController
 */
@RestController
@RequestMapping("/sys/log/error")
@Api(tags = "异常日志")
@AllArgsConstructor
public class SysLogErrorController {
    private final SysLogErrorService sysLogErrorService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("sys:log:error")
    public ResultVO<PageData<SysLogErrorDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysLogErrorDTO> page = sysLogErrorService.page(params);

        return new ResultVO<PageData<SysLogErrorDTO>>().ok(page);
    }

    @GetMapping("/export")
    @ApiOperation("报表导出")
    @LogOperation("报表导出")
    @RequiresPermissions("sys:log:error")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogErrorDTO> list = sysLogErrorService.list(params);
        ExcelUtils.exportExcelToTarget(response, null, "异常日志", list, SysLogErrorExcel.class);
    }
}

