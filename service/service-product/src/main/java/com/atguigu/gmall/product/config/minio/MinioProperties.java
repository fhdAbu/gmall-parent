package com.atguigu.gmall.product.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.config
 * @Author: FHD
 * @CreateTime: 2022-08-25  23:37
 * @Description:
 * @Version: 2.1
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.minio")
//自动把 app.minio下配置的每个属性全部和这个java的属性一一对应
public class MinioProperties {
    String  endpoint;
    String  ak;
    String  sk;
    String  bucketName;
}
