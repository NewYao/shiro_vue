package cn.jnx.mapper;

import cn.jnx.model.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer p_id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer p_id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}