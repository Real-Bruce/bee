package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.bee.modules.sys.dto.DictDataDTO;
import com.bee.modules.sys.service.DictDataService;
import io.swagger.annotations.Api;
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
 * @create 2024/01/10
 * @desc DictDataController
 */
@RestController
@RequestMapping("sys/dict/data")
@Api(tags = "字典数据")
@AllArgsConstructor
public class DictDataController {
    private final DictDataService dictDataService;

    @GetMapping("/page")
    @ApiOperation("字典数据")
    @RequiresPermissions("sys:dict:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictLabel", value = "字典标签", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<DictDataDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<DictDataDTO> page = dictDataService.page(params);
        return new ResultVO<PageData<DictDataDTO>>().ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("字典信息")
    @RequiresPermissions("sys:dict:info")
    public ResultVO<DictDataDTO> get(@PathVariable("id") Long id) {
        DictDataDTO dictDataDTO = dictDataService.get(id);
        return new ResultVO<DictDataDTO>().ok(dictDataDTO);
    }

    @PostMapping
    @ApiOperation("字典保存")
    @LogOperation("字典保存")
    @RequiresPermissions("sys:dict:save")
    public ResultVO save(@RequestBody DictDataDTO dto) {
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        dictDataService.save(dto);
        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("字典修改")
    @LogOperation("字典修改")
    @RequiresPermissions("sys:dict:update")
    public ResultVO update(@RequestBody DictDataDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class ,DefaultGroup.class);

        dictDataService.update(dto);
        return new ResultVO();
    }

    @DeleteMapping
    @ApiOperation("字典删除")
    @LogOperation("字典删除")
    @RequiresPermissions("sys:dict:delete")
    public ResultVO delete(@RequestBody Long[] ids) {
        AssertUtils.isArrayEmpty(ids, "id");

        dictDataService.delete(ids);

        return new ResultVO();
    }

}

