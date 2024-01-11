package com.bee.modules.oss.cloud;

import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @author Bruce
 * @create 2024/01/11
 * @description 腾讯云存储
 */
public class QcloudCloudStorageService extends AbstractCloudStorageService{

    private COSCredentials credentials;
    private ClientConfig clientConfig;

    public QcloudCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        init();
    }

    private void init() {
        // 初始化认证身份信息
        credentials = new BasicCOSCredentials(config.getQcloudSecretId(), config.getQcloudSecretKey());
        // 设置bucket区域
        clientConfig = new ClientConfig(new Region(config.getQcloudRegion()));
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQcloudPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            COSClient cosClient = new COSClient(credentials, clientConfig);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            String bucketName = config.getQcloudBucketName() + "-" + config.getQcloudAppId();
            PutObjectRequest request = new PutObjectRequest(bucketName, path, inputStream, metadata);
            PutObjectResult result = cosClient.putObject(request);

            cosClient.shutdown();
            if (result.getETag() == null) {
                throw new BeeException(ErrorCode.OSS_UPLOAD_FILE_ERROR);
            }
        } catch (Exception e) {
            throw new BeeException(ErrorCode.OSS_UPLOAD_FILE_ERROR);
        }

        return config.getQcloudDomain() + File.separator + path;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix));
    }
}
