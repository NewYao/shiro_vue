package cn.jnx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public User selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Object queryByDate(String[] date) {
        String date1 = date[0];
        String date2 = date[1];
        int num = calcBetweenDate(date1, date2);
        String[] countArr = new String[num];
        System.out.println(num);
        return userMapper.queryByDate(date1,countArr);
    }
    
    public int calcBetweenDate(String start, String end) {  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        Date startDate = null;  
        Date endDate = null;  
        try {  
            startDate = df.parse(start);  
            endDate = df.parse(end);  
        } catch (Exception e) {  
            System.out.println("日期转换出错");  
        }  
        int count = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));  
        return count;  
    }  
}
