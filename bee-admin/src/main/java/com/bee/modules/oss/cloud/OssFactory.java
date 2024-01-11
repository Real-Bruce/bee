package com.bee.modules.oss.cloud;

import com.bee.common.constant.Constant;
import com.bee.common.util.common.SpringContextUtils;
import com.bee.modules.sys.service.SysParamsService;

/**
 * @author Bruce
 * @create 2024/01/10
 * @description 文件上传工厂类
 */
public final class OssFactory {
    private static SysParamsService paramsService;

    static {
        OssFactory.paramsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static AbstractCloudStorageService build() {
        CloudStorageConfig config = paramsService.getValueObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        }

        return null;
    }
}
