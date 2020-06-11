package cn.jnx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jnx.model.Edition_detail;

public interface Edition_detailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Edition_detail record);

    int insertSelective(Edition_detail record);

    Edition_detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Edition_detail record);

    int updateByPrimaryKey(Edition_detail record);
    
    int insertList(@Param("records")List<Edition_detail> records);

	int deleteByEditionId(int edition_id);
}