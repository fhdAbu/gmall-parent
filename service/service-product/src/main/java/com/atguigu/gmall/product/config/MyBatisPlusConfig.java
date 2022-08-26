package com.atguigu.gmall.product.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.config
 * @Author: FHD
 * @CreateTime: 2022-08-24  23:48
 * @Description:
 * @Version: 2.1
 */
@Configuration//告诉springboot这是一个配置类
public class MyBatisPlusConfig {
    //把MyBatisPlus的插件
    @Bean
    public MybatisPlusInterceptor interceptor(){
        //插件主体
        MybatisPlusInterceptor Interceptor = new MybatisPlusInterceptor();

        //加入内部的小插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(true);//页码溢出以后,默认就访问最后一页即可
        //分页插件
        Interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return Interceptor;
    }
}
