package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ExcelUtils;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.bee.modules.sys.dto.SysParamsDTO;
import com.bee.modules.sys.excel.SysParamsExcel;
import com.bee.modules.sys.service.SysParamsService;
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
 * @create 2023/12/14
 * @desc SysParamsController
 */
@RestController
@RequestMapping("/sys/params")
@AllArgsConstructor
@Api(tags = "参数管理")
public class SysParamsController {
    private final SysParamsService sysParamsService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("sys:params:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "paramCode", value = "参数编码", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<SysParamsDTO>> page(@ApiIgnore @RequestBody Map<String, Object> params){
        PageData<SysParamsDTO> page = sysParamsService.page(params);
        return new ResultVO<PageData<SysParamsDTO>>().ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("参数信息")
    @RequiresPermissions("sys:params:info")
    public ResultVO<SysParamsDTO> get(@PathVariable("id") Long id) {
        SysParamsDTO sysParamsDTO = sysParamsService.get(id);
        return new ResultVO<SysParamsDTO>().ok(sysParamsDTO);
    }

    @PostMapping
    @ApiOperation("保存参数")
    @LogOperation("保存参数")
    @RequiresPermissions("sys:params:save")
    public ResultVO save(@RequestBody SysParamsDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysParamsService.save(dto);

        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("参数修改")
    @LogOperation("参数修改")
    @RequiresPermissions("sys:params:update")
    public ResultVO update(@RequestBody SysParamsDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysParamsService.update(dto);

        return new ResultVO();
    }

    @DeleteMapping
    @ApiOperation("参数删除")
    @LogOperation("参数删除")
    @RequiresPermissions("sys:params:delete")
    public ResultVO delete(@RequestBody Long[] ids) {
        AssertUtils.isArrayEmpty(ids, "id");

        sysParamsService.delete(ids);

        return new ResultVO();
    }

    @GetMapping("/export")
    @ApiOperation("列表导出")
    @LogOperation("列表导出")
    @RequiresPermissions("sys:params:export")
    @ApiImplicitParam(name = "paramCode", value = "参数编码", paramType = "query", dataType = "String")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysParamsDTO> paramsDTOList = sysParamsService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "参数管理", paramsDTOList, SysParamsExcel.class);
    }
}

