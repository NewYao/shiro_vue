package cn.jnx.mapper;

import cn.jnx.model.Role_permission;

public interface Role_permissionMapper {
    int deleteByPrimaryKey(Integer r_p_id);

    int insert(Role_permission record);

    int insertSelective(Role_permission record);

    Role_permission selectByPrimaryKey(Integer r_p_id);

    int updateByPrimaryKeySelective(Role_permission record);

    int updateByPrimaryKey(Role_permission record);
}