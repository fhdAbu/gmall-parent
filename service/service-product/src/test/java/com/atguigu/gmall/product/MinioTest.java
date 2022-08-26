package com.atguigu.gmall.product;

import com.atguigu.gmall.product.service.BaseAttrValueService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product
 * @Author: FHD
 * @CreateTime: 2022-08-25  19:58
 * @Description:
 * @Version: 2.1
 *
 */
//@SpringBootTest //可以测试spring容器组件中的任意功能   启动慢
public class MinioTest {
//    @Autowired
//    BaseAttrValueService baseAttrValueService;
//    @Test //jupiter 是junit5的测试
//    void test1(){
//    }
    @Test
    public void  uploadFile() throws Exception {
    try {
        //minio是一个时间敏感的中间件

        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient(
                "http://192.168.200.100:9000",
                "admin",
                "admin123456");

        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists("gmall");
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            //如果桶不存在,则创建一个新的桶
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket("asiatrip");
        }

        // 使用putObject上传一个文件到存储桶中。
        /**
         * string buckName; 桶名
         * string objectName; 对象名,也就是文件名
         * inputStream stream;  文件流
         * E:\javabasics\项目\尚品汇\资料\03 商品图片\品牌\vivo.png
         * putObjectOptions options; 上传的参数设置
         */
        //文件流
        FileInputStream InputStream =
                new FileInputStream("E:\\javabasics\\项目\\尚品汇\\资料\\03 商品图片\\品牌\\vivo.png");
        //文件上传参数
        PutObjectOptions options = new PutObjectOptions(InputStream.available(), -1l);
        options.setContentType("image/png");
        //告诉minio上传的这个文件的内容类型
        minioClient.putObject("gmall","vivo.png",
                InputStream,
                options);
        System.out.println("上传成功");
    } catch(MinioException e) {
        System.out.println("发生错误" + e);
    }
}
}
