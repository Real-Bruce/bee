package com.bee.modules.sys.rest;

import com.bee.common.util.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bruce
 * @create 2023/12/27
 * @description 首页提示
 */
@RestController
public class WelcomeController {

    @GetMapping("/")
    public ResultVO<String> index() {
        String tips = "欢迎使用小蜜蜂权限系统！";
        return new ResultVO<String>().ok(tips);
    }
}
