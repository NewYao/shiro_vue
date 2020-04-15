package cn.jnx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jnx.model.User;

public interface UserMapper {
    /**
     * 根据条件查询用户列表
     * @param user
     * @return
     */
    List<User> getUserListByParams(@Param("user")User user,@Param("beginDateScope") String[] beginDateScope);
    
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String username);
    
    User selectByPhone(String phone);
}