package cn.jnx.mapper;

import cn.jnx.model.Edition_detail;

public interface Edition_detailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Edition_detail record);

    int insertSelective(Edition_detail record);

    Edition_detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Edition_detail record);

    int updateByPrimaryKey(Edition_detail record);
}