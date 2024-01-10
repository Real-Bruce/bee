package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.page.PageData;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.common.util.validator.group.UpdateGroup;
import com.bee.modules.sys.dto.DictTypeDTO;
import com.bee.modules.sys.service.DictTypeService;
import com.bee.modules.sys.vo.DictTypeVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/09
 * @desc DictTypeController
 */
@RestController
@RequestMapping("/dictType")
@Api(tags = "字典类型")
@AllArgsConstructor
public class DictTypeController {
    private final DictTypeService dictTypeService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("sys:dict:page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String")
    })
    public ResultVO<PageData<DictTypeDTO>> page (@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<DictTypeDTO> page = dictTypeService.page(params);
        return new ResultVO<PageData<DictTypeDTO>>().ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("ID查询")
    @RequiresPermissions("sys:dict:info")
    public ResultVO<DictTypeDTO> get(@PathVariable("id") Long id) {
        DictTypeDTO dictTypeDTO = dictTypeService.get(id);
        return new ResultVO<DictTypeDTO>().ok(dictTypeDTO);
    }

    @PostMapping
    @ApiOperation("字典类型保存")
    @LogOperation("字典类型保存")
    @RequiresPermissions("sys:dict:save")
    public ResultVO save(@RequestBody DictTypeDTO dto) {
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        dictTypeService.save(dto);

        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("字典类型修改")
    @LogOperation("字典类型修改")
    @RequiresPermissions("sys:dict:update")
    public ResultVO update(@RequestBody DictTypeDTO dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        dictTypeService.update(dto);

        return new ResultVO();
    }

    @DeleteMapping
    @ApiOperation("字典类型删除")
    @LogOperation("字典类型删除")
    @RequiresPermissions("sys:dict:delete")
    public ResultVO delete(@RequestBody Long[] ids) {
        AssertUtils.isArrayEmpty(ids, "id");

        dictTypeService.delete(ids);

        return new ResultVO();
    }

    @GetMapping("/all")
    @ApiOperation("所有字典类型")
    public ResultVO<List<DictTypeVO>> all () {
        List<DictTypeVO> dictTypeVOS = dictTypeService.allList();
        return new ResultVO<List<DictTypeVO>>().ok(dictTypeVOS);
    }

}

