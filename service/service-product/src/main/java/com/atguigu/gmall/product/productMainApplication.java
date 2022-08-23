package com.atguigu.gmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product
 * @Author: FHD
 * @CreateTime: 2022-08-22  22:27
 * @Description:
 * @Version: 2.1
 */
//@SpringBootApplication
//@EnableCircuitBreaker
//@EnableDiscoveryClient
@SpringCloudApplication
@MapperScan("com.atguigu.gmall.product.mapper") //自动扫描这个包下的所有mapper接口
public class productMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(productMainApplication.class,args);
    }
}
