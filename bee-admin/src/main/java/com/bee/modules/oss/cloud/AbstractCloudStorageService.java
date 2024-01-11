package com.bee.modules.oss.cloud;

import cn.hutool.core.util.StrUtil;
import com.bee.common.util.common.DateUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author Bruce
 * @create 2024/01/10
 * @description 通用云存储服务
 */
public abstract class AbstractCloudStorageService {

    // 云存储配置
    CloudStorageConfig config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 文件路径
     */
    public String getPath(String prefix, String suffix) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String path = DateUtils.format(new Date(), "yyyyMMdd") + File.separator + uuid;

        if (StrUtil.isNotBlank(prefix)) {
            path = prefix + File.separator + path;
        }

        return path + "." + suffix;
    }

    /**
     * 文件上传-字节流
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传带后缀-字节流
     * @param data 文件字节数组
     * @param suffix 后缀
     * @return 返回https地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传-字节流
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传带后缀-字节流
     * @param inputStream 文件字节数组
     * @param suffix 后缀
     * @return 返回https地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);
}
