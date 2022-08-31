package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.controller
 * @Author: FHD
 * @CreateTime: 2022-08-29  12:26
 * @Description:
 * @Version: 2.1
 */
@RestController
public class ThreadPoolController {
    @Autowired
    ThreadPoolExecutor threadPoolExecutor;
    @GetMapping("/close/pool")
    public Result closePool(){
        threadPoolExecutor.shutdown(); //关闭线程池
        return Result.ok();
    }
    @GetMapping("/monitor/pool")
    public Result monitorThreadPool(){
        int poolSize = threadPoolExecutor.getCorePoolSize();
        long taskCount = threadPoolExecutor.getTaskCount();//当前统计的任务数
        return Result.ok(poolSize+"====="+taskCount);
    }
}
