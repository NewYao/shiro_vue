package cn.jnx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/")
    @RequiresPermissions(value= {"user:select"})
    public ReturnJson getUserList(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size, User user,String[] dateScope) {
        JSONObject userList = userService.getUserList(page, size, user,dateScope);
        return new ReturnJson().ok().data(userList);
    }
    @PostMapping("/delete")
    @RequiresPermissions(value= {"user:delete"})
    public ReturnJson deleteUserById( Integer id) {
        if(userService.deleteUserById(id) == 1) {
            return new ReturnJson().ok().message("删除成功！");
        }
        return new ReturnJson().fail().message("删除失败！");
    }
    
    @PostMapping("/update")
    @RequiresPermissions(value= {"user:modify"})
    public ReturnJson updateUserInfo(User user) {
        if(userService.updateUser(user) == 1) {
            return new ReturnJson().ok().message("更改成功！");
        }
        return new ReturnJson().fail().message("更改失败！");
    }
    
    @PostMapping("/add")
    @RequiresPermissions(value= {"user:add"})
    public ReturnJson addUser(User user) {
        if(userService.addUser(user) == 1) {
            return new ReturnJson().ok().message("新增成功！");
        }
        return new ReturnJson().fail().message("新增失败！");
    }
    
    
    @PostMapping("/queryByDate")
    @RequiresPermissions(value= {"user:select"})
    public ReturnJson queryByDate(String[] date) {
        return new ReturnJson().ok().data(userService.queryByDate(date));
    }
    
}
