package com.atguigu.gmall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.meta.Exclusive;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall
 * @Author: FHD
 * @CreateTime: 2022-08-26  09:11
 * @Description:
 * @Version: 2.1
 */

/**
 * 不要启用数据源的自动配置
 * 1.DataSourcesAutoConfiguration就会失效
 * 2. 在application.yaml中配置即可
 * 前端项目 --页面跳转与数据渲染
 */
@EnableFeignClients //开启远程调用功能
@SpringCloudApplication /*这是以下三个注解的合体*/
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class webAllMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(webAllMainApplication.class,args);
    }
}
