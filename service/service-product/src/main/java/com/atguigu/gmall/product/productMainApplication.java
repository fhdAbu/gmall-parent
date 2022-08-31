package com.atguigu.gmall.product;

import com.atguigu.gmall.common.annotation.EnableThreadPool;
import com.atguigu.gmall.common.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableThreadPool
@EnableTransactionManagement  //开启基于注解的事务
@SpringCloudApplication
@Import(Swagger2Config.class)
@MapperScan("com.atguigu.gmall.product.mapper") //自动扫描这个包下的所有mapper接口
public class productMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(productMainApplication.class,args);
    }
}
