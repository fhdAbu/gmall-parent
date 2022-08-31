package com.atguigu.gmall.item;

import com.atguigu.gmall.common.annotation.EnableThreadPool;
import com.atguigu.gmall.common.config.threadpool.AppThreadPoolAutoConfiguration;
import com.atguigu.gmall.common.config.threadpool.AppThreadPoolProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item
 * @Author: FHD
 * @CreateTime: 2022-08-27  14:33
 * @Description:
 * @Version: 2.1
 */

/**
 * 公共配置搬家到service-util
 * 当前项目依赖了 service-util
 *
 * 但当前项目启动指挥扫描ItemMainApplication所在包下的组件
 */
@EnableThreadPool
@SpringCloudApplication
@EnableFeignClients
public class ItemMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemMainApplication.class,args);
    }
}
