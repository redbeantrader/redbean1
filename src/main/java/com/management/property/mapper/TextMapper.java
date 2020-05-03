package com.management.property.mapper;

import com.management.property.pojo.Text;

import java.util.List;

public interface TextMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Text record);

    int insertSelective(Text record);

    Text selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Text record);

    int updateByPrimaryKey(Text record);

    List<Text> selectAll(Integer limit, Integer page);

    int count();
}