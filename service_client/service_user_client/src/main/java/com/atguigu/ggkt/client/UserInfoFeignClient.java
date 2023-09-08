package com.atguigu.ggkt.client;

import com.atguigu.ggkt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: lishang2
 * @create: 2023-09-08
 */
@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

    /**
     * feign接口查询用户信息
     * @param id 用户id
     * @return UserInfo
     */
    @GetMapping("/admin/user/userInfo/inner/getById/{id}")
    UserInfo getById(@PathVariable Long id);

}
