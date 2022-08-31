package com.atguigu.gmall.common.config.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.config
 * @Author: FHD
 * @CreateTime: 2022-08-29  10:18
 * @Description:
 * @Version: 2.1
 */

/**
 * 配置线程池的
 * AppThreadPoolProperties里面的所有属性和指定配置绑定
 * AppThreadPoolProperties 组件自动放到容器中
 *
 */
//开启自动化属性绑定配置
@EnableConfigurationProperties(AppThreadPoolProperties.class)
@Configuration
public class AppThreadPoolAutoConfiguration {
    @Autowired
    AppThreadPoolProperties threadPoolProperties;
    @Value("${spring.application.name}")
    String applicationName;
    @Bean
    public ThreadPoolExecutor coreExecutor(){
         /**
          * int corePoolSize: 核心线程数 --> 值班人员(固定) = cpu核心数 **** 4
          * int maximumPoolSize: 最大线程数 -->最大总数 **** 8
          * long keepAliv eTime:线程存活时间
          *  TimeUnit unit:时间单位
          *  BlockingQueue<Runnable> workQueue, :阻塞队列 (此处为调优的关键) 大小需要合理
          *         new ArrayBlockingQueue<>(10); //底层队列是一个数组
          *         new LinkedBlockingQueue<>(10);//底层是一个链表
          *         他两个的区别就是数组与链表的区别???
          *                 **** 数组是连续空间(不推荐)   ****链表不连续(可以利用碎片化空间)
          *  ThreadFactory threadFactory: 线程工厂  自定义创建线程
          *  RejectedExecutionHandler handler
          *
          *     2000/s: 队列大小根据接口吞吐量标准调整   假设吞吐量占用100mb内存,设置 最小占用内存-Xms100mb -Xmx200mb
          */



    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            threadPoolProperties.core,
            threadPoolProperties.max,
            threadPoolProperties.keepAliveTime,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(threadPoolProperties.queueSize),//队列的大小由项目最终能占用的最大内存决定(用压力测试来调整)
            new ThreadFactory() {//负责给线程池创建线程
                int i = 0;//记录线程自增id
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName(applicationName+"[core-thread-"+i++ +"]");//表示第几个核心线程
                    return thread;
                }
            },
            //生产环境使用 callerRunPolicy,保证就算线程池满了,不能提交的任务
            // 由当前线程自己以同步的方式去执行
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    return executor;
    }
}
