package com.atguigu.gmall.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product
 * @Author: FHD
 * @CreateTime: 2022-08-29  19:49
 * @Description:
 * @Version: 2.1
 */
@SpringBootTest
public class ThreadPoolTest {
    @Autowired
    ThreadPoolExecutor executor;

    @Test
    void testPool(){
        for (int i = 0; i < 100; i++) {

            executor.submit(()->{
                System.out.println(Thread.currentThread().getName() +":"+ UUID.randomUUID().toString().substring(4));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(100000000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
