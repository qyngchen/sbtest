package com.qyngchen.sbminio.service.impl;

import com.qyngchen.sbminio.service.MinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.server.bucket}")
    private String bucketName;

    @Override
    public void uploadFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            if (!minioClient.bucketExists(bucketName)) {
                minioClient.makeBucket(bucketName);
            }
            System.out.println(file.getName());
            minioClient.putObject(bucketName, "1d1140f4-e915-4005-9349-992ce172052b", fileInputStream, null);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public InputStream downloadFiel() {
        try {
            String objectUrl = minioClient.getObjectUrl(bucketName, "39f00e18-e203-449b-97c4-e95f7c7b083d");
            System.out.println("objectUrl---------------" + objectUrl);
            InputStream inputStream = minioClient.getObject(bucketName, "39f00e18-e203-449b-97c4-e95f7c7b083d");
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
