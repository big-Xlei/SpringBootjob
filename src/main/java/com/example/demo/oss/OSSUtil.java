package com.example.demo.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.UUID;

/**
 * Copyright (C),2021/3/1 ,脉策科技杭州团队
 *
 * @ description:OSS上传下载功能
 * @ author:wangjie
 * @ data:2021/3/1 10:28
 */
@Slf4j
@Component
public class OSSUtil {


    @Value("${oss.ossEndpoint}")
    private String ossEndpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;

    /**
     * OSS流式上传URL
     */
    public String upload(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(ossEndpoint, accessKeyId, accessKeySecret);
        try {
            String fileName = file.getOriginalFilename();
            String objectName = UUID.randomUUID().toString().replace("-", "").substring(0, 15) + fileName;
            InputStream ips = file.getInputStream();
            ossClient.putObject(bucketName, objectName, ips);
            return objectName;
        } catch (Exception e) {
            log.info(file.getOriginalFilename() +"文件上传失败");
            return "文件上传失败";
        } finally {
            // 关闭流
            ossClient.shutdown();
        }
    }

    /**
     * OSS流式下载
     */
    public void download(String objectName, HttpServletResponse response) {
        ServletOutputStream out = null;
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ossEndpoint, accessKeyId, accessKeySecret);
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            InputStream objectContent = ossObject.getObjectContent();
            //获取文件类型
            String contentType = ossObject.getObjectMetadata().getContentType();
            response.setContentType(contentType);

            out = response.getOutputStream();
            // 读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = objectContent.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            // 关闭流
            ossClient.shutdown();
        }
    }
}
