package cn.jnx.mapper;

import java.util.List;

import cn.jnx.model.Role;

public interface RoleMapper {
	
	List<Role> selectAllRoles();
	
    int deleteByPrimaryKey(Integer r_id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer r_id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}