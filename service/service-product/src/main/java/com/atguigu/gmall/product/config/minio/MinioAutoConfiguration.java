package com.atguigu.gmall.product.config.minio;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.config
 * @Author: FHD
 * @CreateTime: 2022-08-25  23:12
 * @Description:
 * @Version: 2.1
 */

import com.atguigu.gmall.product.config.minio.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio的自动配置类
 */
@Configuration  //是容器中的组件
public class MinioAutoConfiguration {
    @Autowired
    MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = new MinioClient(
                minioProperties.getEndpoint(),
                minioProperties.getAk(),
                minioProperties.getSk(),
                minioProperties.getBucketName()
        );
       String bucketName = minioProperties.getBucketName();
        boolean b = minioClient.bucketExists(bucketName);
        if(!b){
            minioClient.makeBucket(bucketName);
        }
        return minioClient;
    }
}
