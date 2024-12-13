package org.asuka.lease.web.admin.service.impl;

import io.minio.*;
import org.asuka.lease.common.minio.MinioProperties;
import org.asuka.lease.web.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 访问minio资源时,是下载还是直接展示
 * 取决于响应头的Content-Type
 * 如果是image/jpeg则是直接展示
 * 如果是application/octet-stream则是下载
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        String objectUrl;
        try {
            //判断桶是否存在
            boolean flag = minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .build());
            if (!flag) {
                //创建桶
                minioClient.makeBucket(
                        MakeBucketArgs
                                .builder()
                                .bucket(minioProperties.getBucketName())
                                .build());
                //设置权限
                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs
                                .builder()
                                .bucket(minioProperties.getBucketName())
                                .config(this.createBucketPolicyConfig(minioProperties.getBucketName()))
                                .build());

            }
            //上传文件
            String filename = this.getDistinctFileName(file);
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .object(filename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            //返回url
            objectUrl = this.getObjectUrl(filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objectUrl;
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }

    private String getDistinctFileName(MultipartFile file) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

    private String getObjectUrl(String filename) {
        return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + filename;
    }
}
