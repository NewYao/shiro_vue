package cn.jnx.service;

import com.alibaba.fastjson.JSONObject;

import cn.jnx.model.User;

public interface UserService {

    public JSONObject getUserList(Integer page, Integer size, User user, String[] beginDateScope);
    
    public int deleteUserById(Integer id);

    public int updateUser(User user);
    
    public User selectByUserName(String username);
    
    public User selectByPhone(String phone);

    public int addUser(User user);

    public Object queryByDate(String[] date);
}
