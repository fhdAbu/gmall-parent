package com.atguigu.gmall.common.config.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.config
 * @Author: FHD
 * @CreateTime: 2022-08-29  11:06
 * @Description:
 * @Version: 2.1
 */
@Data
@Component//一个组件
@ConfigurationProperties(prefix = "app.thread-pool")
public class AppThreadPoolProperties {
    Integer core = 2;
    Integer max = 4;
    Integer queueSize = 200;
    Long keepAliveTime = 300L;
}
