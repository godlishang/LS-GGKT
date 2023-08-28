package com.atguigu.ggkt.vod.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : lishang
 * @date : 2023/3/7 15:44
 */
@MapperScan("com.atguigu.ggkt.vod.mapper")
@Configuration
public class VodConfig {

    /**
     * 分页插件
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}

