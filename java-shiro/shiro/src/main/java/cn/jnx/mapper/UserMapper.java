package cn.jnx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jnx.model.User;
import cn.jnx.model.dto.User_dto;

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
    
    User_dto selectByUserName(String username);
    
    User selectByPhone(String phone);

    List<Map<String,Object>> queryByDate(@Param("date")String date, @Param("countArr")String[] countArr);
}