package cn.jnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jnx.model.ReturnJson;
import cn.jnx.model.User;
import cn.jnx.service.UserService;

@RestController
@RequestMapping("/user/base")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ReturnJson getUserList(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size, User user,String[] beginDateScope) {
        JSONObject userList = userService.getUserList(page, size, user,beginDateScope);
        return new ReturnJson().ok().data(userList);
    }
}
