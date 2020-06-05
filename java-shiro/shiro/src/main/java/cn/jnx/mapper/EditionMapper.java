package cn.jnx.mapper;

import java.util.List;

import cn.jnx.model.Edition;

public interface EditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Edition record);

    int insertSelective(Edition record);

    Edition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Edition record);

    int updateByPrimaryKey(Edition record);
    
    List<Edition> selectAll();
}