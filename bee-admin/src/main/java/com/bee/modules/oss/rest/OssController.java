package com.bee.modules.oss.rest;

import cn.hutool.core.io.file.FileNameUtil;
import com.bee.common.annotation.LogOperation;
import com.bee.common.constant.Constant;
import com.bee.common.exception.ErrorCode;
import com.bee.common.page.PageData;
import com.bee.common.util.ResultVO;
import com.bee.common.util.common.JsonUtils;
import com.bee.common.util.validator.ValidatorUtils;
import com.bee.common.validator.group.AliyunGroup;
import com.bee.common.validator.group.QcloudGroup;
import com.bee.common.validator.group.QiniuGroup;
import com.bee.modules.oss.cloud.CloudStorageConfig;
import com.bee.modules.oss.cloud.OssFactory;
import com.bee.modules.oss.entity.Oss;
import com.bee.modules.oss.service.OssService;
import com.bee.modules.sys.service.SysParamsService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.awt.image.Kernel;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc OssController
 */
@RestController
@RequestMapping("/sys/oss")
@AllArgsConstructor
public class OssController {
    private final OssService ossService;
    private final SysParamsService paramsService;

    private final static String CLOUD_KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    @GetMapping("/page")
    @ApiOperation("分页查询文件oss")
    @RequiresPermissions("sys:oss:all")
    public ResultVO<PageData<Oss>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<Oss> page = ossService.page(params);
        return new ResultVO<PageData<Oss>>().ok(page);
    }

    @GetMapping("/info")
    @ApiOperation("云存储配置")
    @RequiresPermissions("sys:oss:all")
    public ResultVO<CloudStorageConfig> info() {
        CloudStorageConfig config = paramsService.getValueObject(CLOUD_KEY, CloudStorageConfig.class);

        return new ResultVO<CloudStorageConfig>().ok(config);
    }

    @PostMapping
    @ApiOperation("保存云存储配置")
    @LogOperation("保存云存储配置")
    @RequiresPermissions("sys:oss:all")
    public ResultVO saveConfig(@RequestBody CloudStorageConfig config) {

        ValidatorUtils.validateEntity(config);

        if (Constant.CloudService.QINIU.getValue() == config.getType()) {
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (Constant.CloudService.ALIYUN.getValue() == config.getType()) {
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (Constant.CloudService.QCLOUD.getValue() == config.getType()) {
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        paramsService.updateValueByCode(CLOUD_KEY, JsonUtils.toJsonString(config));

        return new ResultVO();
    }

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    @RequiresPermissions("sys:oss:all")
    public ResultVO<Map<String, Object>> upload(@RequestParam("file")MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return new ResultVO<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        String suffix = FileNameUtil.getSuffix(file.getOriginalFilename());
        String url = OssFactory.build().uploadSuffix(file.getBytes(), suffix);

        Oss oss = new Oss();
        oss.setUrl(url);
        oss.setCreateDate(new Date());
        ossService.insert(oss);

        HashMap<String, Object> data = new HashMap<>();
        data.put("src", url);

        return new ResultVO<Map<String, Object>>().ok(data);
    }

    @DeleteMapping
    @ApiOperation("删除文件")
    @LogOperation("删除文件")
    @RequiresPermissions("sys:oss:all")
    public ResultVO delete(@RequestBody Long[] ids) {
        ossService.deleteBachIds(Arrays.asList(ids));

        return new ResultVO();
    }


}

