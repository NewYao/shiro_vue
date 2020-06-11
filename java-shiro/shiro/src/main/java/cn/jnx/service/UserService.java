package cn.jnx.service;

import com.alibaba.fastjson.JSONObject;

import cn.jnx.model.ReturnJson;
import cn.jnx.model.User;
import cn.jnx.model.dto.User_dto;

public interface UserService {

    public JSONObject getUserList(Integer page, Integer size, User user, String[] beginDateScope);
    
    public int deleteUserById(Integer id);

    public int updateUser(User user);
    
    public User_dto selectByUserName(String username);
    
    public User selectByPhone(String phone);

    public boolean addUser(User user,Integer r_id);

    public Object queryByDate(String[] date);

	public ReturnJson modifyPwd(Integer id, String oldpwd, String newPwd);
}
