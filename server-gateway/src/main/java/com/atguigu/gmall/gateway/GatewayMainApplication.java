package com.atguigu.gmall.gateway;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.gateway
 * @Author: FHD
 * @CreateTime: 2022-08-22  18:28
 * @Description:
 * @Version: 2.1
 */
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 * 主启动类
 */


//@EnableCircuitBreaker //开启服务熔断降级 [1.导入服务发现jar包,2.使用这个注解]
//@EnableDiscoveryClient //开启服务发现[1.导入服务发现jar包,2.使用这个注解]
//@SpringBootApplication
@SpringCloudApplication  //包含以上三个注解
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class,args);
    }
}
