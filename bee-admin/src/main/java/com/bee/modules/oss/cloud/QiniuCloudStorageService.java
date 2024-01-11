package com.bee.modules.oss.cloud;

import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;

import java.io.File;
import java.io.InputStream;

/**
 * @author Bruce
 * @create 2024/01/11
 * @description 七牛云存储
 */
public class QiniuCloudStorageService extends AbstractCloudStorageService{

    private UploadManager uploadManager;
    private String token;

    public QiniuCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        init();
    }

    private void init() {
        UploadManager manager = new UploadManager(new Configuration(Region.autoRegion()));
        token = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey()).uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response response = uploadManager.put(data, path, token);
            if (!response.isOK()) {
                throw new  BeeException(ErrorCode.OSS_UPLOAD_FILE_ERROR, response.toString());
            }
        } catch (Exception e) {
            throw new BeeException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }

        return config.getQiniuDomain() + File.separator + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (Exception e) {
            throw new BeeException(ErrorCode.OSS_UPLOAD_FILE_ERROR);
        }
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }
}
