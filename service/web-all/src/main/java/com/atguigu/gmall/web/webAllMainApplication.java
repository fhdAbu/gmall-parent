package com.atguigu.gmall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.meta.Exclusive;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall
 * @Author: FHD
 * @CreateTime: 2022-08-26  09:11
 * @Description:
 * @Version: 2.1
 */
//@SpringCloudApplication
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableCircuitBreaker
public class webAllMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(webAllMainApplication.class,args);
    }
}
