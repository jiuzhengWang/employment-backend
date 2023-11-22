package org.excellence.yunnanemploymentbackend.controller;

import lombok.Data;
import org.excellence.yunnanemploymentbackend.entity.Response;
import org.excellence.yunnanemploymentbackend.entity.User;
import org.excellence.yunnanemploymentbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "login")
    public @ResponseBody Response<Boolean> login(@RequestBody User user) {
        final var result = userService.login(user);
        if (!result.equals("success")) {
            // response 的success指的是"响应是否成功", 不针对任何请求, 前端要的值在data中
            return new Response<>(true, result, false);
        }
        return new Response<>(true, result, true);
    }

    @PostMapping(path = "register")
    public @ResponseBody Response<Boolean> register(@RequestBody User user) {
        // TODO: 省给企业注册账号时应当指明userType
        final var result = userService.register(user);
        if (!result.equals("success")) {
            return new Response<>(true, result, false);
        }
        return new Response<>(true, result, true);
    }


}
