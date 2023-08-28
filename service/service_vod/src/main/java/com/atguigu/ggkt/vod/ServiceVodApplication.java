package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : lishang
 * @date : 2023/2/27 16:49
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
public class ServiceVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }

}

