package com.atguigu.gmall.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item
 * @Author: FHD
 * @CreateTime: 2022-08-31  15:28
 * @Description:
 * @Version: 2.1
 */
@SpringBootTest
public class redisTest {
    /**
     * RedisTemplate,JdbcTemplate,RabbitTemplate:对应的中间件的操作工具
     */
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    void saveTest(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("hello","world");
        System.out.println("redis保存完成");

        String s = ops.get("hello");
        System.out.println(s);
    }
}
