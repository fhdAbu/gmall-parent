package com.atguigu.gmall.common.annotation;

import com.atguigu.gmall.common.config.threadpool.AppThreadPoolAutoConfiguration;
import com.atguigu.gmall.common.config.threadpool.AppThreadPoolProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.common.annotation
 * @Author: FHD
 * @CreateTime: 2022-08-29  19:10
 * @Description:
 * @Version: 2.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(AppThreadPoolAutoConfiguration.class)
/**
 * 1.导入AppThreadPoolProperties组件
 * 2.开启 @EnableConfigurationProperties(AppThreadPoolProperties.class)这个配置
 *    --和配置文件绑好
 *    --AppThreadPoolProperties 放到容器
 * 3.AppThreadPoolAutoConfiguration 给容器中放一个ThreadPoolExecutor
 *
 * 效果 : 随时@Autowired ThreadPoolExecutor 即可,也很方便改配置
 */
public @interface EnableThreadPool {
}
