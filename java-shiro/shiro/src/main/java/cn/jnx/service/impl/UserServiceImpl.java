package cn.jnx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jnx.mapper.UserMapper;
import cn.jnx.model.PageUtil;
import cn.jnx.model.User;
import cn.jnx.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getUserList(Integer page, Integer size, User user, String[] beginDateScope) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.getUserListByParams(user, beginDateScope);
        // 得到分页的结果对象
        PageInfo<User> PageInfo = new PageInfo<>(userList);
        // 将结果存入map进行传送
        JSONObject pageInfo = PageUtil.formatPage(PageInfo);
        return pageInfo;
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
