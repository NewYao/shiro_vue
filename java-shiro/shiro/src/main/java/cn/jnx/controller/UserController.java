package cn.jnx.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequiresUser
    public ReturnJson getUserList(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size, User user,String[] beginDateScope) {
        JSONObject userList = userService.getUserList(page, size, user,beginDateScope);
        return new ReturnJson().ok().data(userList);
    }
    @PostMapping("/delete")
    @RequiresRoles("admin")
    public ReturnJson deleteUserById( Integer id) {
        if(userService.deleteUserById(id) == 1) {
            return new ReturnJson().ok().message("删除成功！");
        }
        return new ReturnJson().fail().message("删除失败！");
    }
    
    @PostMapping("/update")
    @RequiresRoles("user")
    public ReturnJson updateUserInfo(User user) {
        if(userService.updateUser(user) == 1) {
            return new ReturnJson().ok().message("更改成功！");
        }
        return new ReturnJson().fail().message("更改失败！");
    }
    
    @PostMapping("/add")
    @RequiresRoles("admin")
    public ReturnJson addUser(User user) {
        if(userService.addUser(user) == 1) {
            return new ReturnJson().ok().message("新增成功！");
        }
        return new ReturnJson().fail().message("新增失败！");
    }
    
    
    @PostMapping("/queryByDate")
    public ReturnJson queryByDate(String[] date) {
        return new ReturnJson().ok().data(userService.queryByDate(date));
    }
    
}
