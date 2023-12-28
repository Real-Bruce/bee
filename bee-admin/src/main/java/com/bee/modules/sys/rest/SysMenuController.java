package com.bee.modules.sys.rest;

import com.bee.common.annotation.LogOperation;
import com.bee.common.exception.ErrorCode;
import com.bee.common.util.ResultVO;
import com.bee.common.util.validator.AssertUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.util.validator.group.DefaultGroup;
import com.bee.modules.security.service.ShiroService;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.MenuTypeEnum;
import com.bee.modules.sys.dto.SysMenuDTO;
import com.bee.modules.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysMenuController
 */
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
@Api(tags = "资源管理")
public class SysMenuController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @GetMapping("/nav")
    @ApiOperation("菜单导航")
    public ResultVO<List<SysMenuDTO>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> menuDTOS = sysMenuService.listByUser(user, MenuTypeEnum.MENU.getValue());
        return new ResultVO<List<SysMenuDTO>>().ok(menuDTOS);
    }

    @GetMapping("/permissions")
    @ApiOperation("权限标识")
    public ResultVO<Set<String>> permissions() {
        UserDetail user = SecurityUser.getUser();
        Set<String> userPermissions = shiroService.getUserPermissions(user);
        return new ResultVO<Set<String>>().ok(userPermissions);
    }

    @GetMapping("/list")
    @ApiOperation("菜单列表")
    @ApiImplicitParam(name = "type", value = "菜单类型 0：菜单 1：按钮  null：全部", paramType = "query", dataType = "int")
    @RequiresPermissions("sys:menu:list")
    public ResultVO<List<SysMenuDTO>> list(Integer type) {
        List<SysMenuDTO> menuDTOS = sysMenuService.listByType(type);
        return new ResultVO<List<SysMenuDTO>>().ok(menuDTOS);
    }

    @GetMapping("/{id}")
    @ApiOperation("资源信息")
    @RequiresPermissions("sys:menu:info")
    public ResultVO<SysMenuDTO> get(@PathVariable("id") Long id) {
        SysMenuDTO menuDTO = sysMenuService.getById(id);
        return new ResultVO<SysMenuDTO>().ok(menuDTO);
    }

    @PostMapping
    @ApiOperation("保存资源")
    @LogOperation("保存资源")
    @RequiresPermissions("sys:menu:save")
    public ResultVO save(@RequestBody SysMenuDTO dto) {
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.save(dto);
        return new ResultVO();
    }

    @PutMapping
    @ApiOperation("修改资源")
    @LogOperation("修改资源")
    @RequiresPermissions("sys:menu:update")
    public ResultVO update(@RequestBody SysMenuDTO dto) {
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.update(dto);
        return new ResultVO();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除资源")
    @LogOperation("删除资源")
    @RequiresPermissions("sys:menu:delete")
    public ResultVO delete(@PathVariable("id") Long id) {
        AssertUtils.isNull(id, "id");
        // 存在子资源则不能删除
        List<SysMenuDTO> menuDTOS = sysMenuService.listByPid(id);
        if (menuDTOS.size() > 0) {
            return new ResultVO().error(ErrorCode.SUB_MENU_EXIST);
        }

        sysMenuService.delete(id);
        return new ResultVO();
    }

    @GetMapping("/select")
    @ApiOperation("角色菜单权限")
    @RequiresPermissions("sys:menu:select")
    public ResultVO<List<SysMenuDTO>> select() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> menuDTOS = sysMenuService.listByUser(user, null);
        return new ResultVO<List<SysMenuDTO>>().ok(menuDTOS);
    }

}

