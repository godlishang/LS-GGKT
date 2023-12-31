package com.atguigu.ggkt.activity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lishang2
 * @create: 2023-09-08
 */
@Configuration
@MapperScan("com.atguigu.ggkt.activity.mapper")
public class ActivityConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
