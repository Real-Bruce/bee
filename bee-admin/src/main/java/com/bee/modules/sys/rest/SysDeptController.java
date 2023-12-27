package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.AddGroup;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.modules.sys.dto.SysDeptDTO;
import com.bee.modules.sys.dto.SysUserDTO;
import com.bee.modules.sys.entity.SysDept;
import com.bee.modules.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/20
 * @desc SysDeptController
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门管理")
@AllArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("/list")
    @ApiOperation("查询列表")
    @RequiresPermissions("sys:dept:list")
    public ResultVO<List<SysDeptDTO>> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));
        return new  ResultVO<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("/{id}")
    @ApiOperation("ID获取")
    @RequiresPermissions("sys:dept:save")
    public ResultVO<SysDeptDTO> get(@PathVariable("id") Long id) {
        SysDeptDTO deptDTO = sysDeptService.getById(id);
        return new ResultVO<SysDeptDTO>().ok(deptDTO);
    }
    @PostMapping("/save")
    @ApiOperation("保存数据")
    @LogOperation("保存数据")
    @RequiresPermissions("sys:dept:save")
    public ResultVO save(@RequestBody SysDeptDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysDeptService.save(dto);
        return new ResultVO();
    }

    @PutMapping("/update")
    @ApiOperation("修改数据")
    @LogOperation("修改数据")
    @RequiresPermissions("sys:dept:update")
    public ResultVO update(@RequestBody SysDeptDTO dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysDeptService.update(dto);
        return new ResultVO();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("ID删除")
    @LogOperation("ID删除")
    @RequiresPermissions("sys:dept:delete")
    public ResultVO delete(@PathVariable("id") Long id) {
        AssertUtils.isNull(id, "id");
        sysDeptService.delete(id);
        return new ResultVO();
    }
}

