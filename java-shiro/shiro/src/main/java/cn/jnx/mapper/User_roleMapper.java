package cn.jnx.mapper;

import cn.jnx.model.User_role;

public interface User_roleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_role record);

    int insertSelective(User_role record);

    User_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User_role record);

    int updateByPrimaryKey(User_role record);
}