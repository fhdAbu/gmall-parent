package com.atguigu.gmall.item;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item
 * @Author: FHD
 * @CreateTime: 2022-08-31  21:00
 * @Description:
 * @Version: 2.1
 */
//布隆过滤器,使用前先导包---->guava
public class BloomFilterTest {
    /**
     *
     * Funnel<? super T> funnel.
     * int expectedInsertions ,期望插入的数量: 1w
     * double fpp: false positive probability : 错误判定的几率  误判率越高,bloom存东西的hash次数越多,占位越多
     */
    @Test
    void bloomTest(){
        BloomFilter<Long> filter = BloomFilter.create(Funnels.longFunnel(),10000,0.000001);
        for (long i = 0; i <20; i++) {
            filter.put(i);
        }
        //判定有没有
        System.out.println(filter.mightContain(1l));
        System.out.println(filter.mightContain(20l));
        System.out.println(filter.mightContain(99l));
    }

}
