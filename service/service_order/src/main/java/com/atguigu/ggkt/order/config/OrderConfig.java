package com.atguigu.ggkt.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lishang2
 * @create: 2023-09-06
 */
@Configuration
@MapperScan("com.atguigu.ggkt.order.mapper")
public class OrderConfig {

    @Bean
    public PaginationInterceptor PaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
