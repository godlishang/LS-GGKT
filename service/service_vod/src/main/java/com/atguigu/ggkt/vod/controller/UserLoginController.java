package com.atguigu.ggkt.vod.controller;

import com.atguigu.resultUtils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author : lishang
 * @date : 2023/5/31 15:05
 * CrossOrigin 解决跨域问题
 */
@RestController
@RequestMapping("/admin/vod/user")
//@CrossOrigin
public class UserLoginController {

    @PostMapping("login")
    public Result<Object> login(){
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("token","admin-token");

        return Result.ok(map);
    }

    @GetMapping("info")
    public Result<Object> info(){
        //{"code":20000,
        // "data":{"roles":["admin"],"introduction":"I am a super administrator",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
        // "name":"Super Admin"}}
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles",new String[]{"admin"});
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");

        return Result.ok(map);
    }
}

