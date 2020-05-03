package com.management.property.mapper;

import com.management.property.pojo.Costs;

import java.util.List;

public interface CostsMapper {
    int deleteByPrimaryKey(Integer costsId);

    int insert(Costs record);

    int insertSelective(Costs record);

    Costs selectByPrimaryKey(Integer costsId);

    int updateByPrimaryKeySelective(Costs record);

    int updateByPrimaryKey(Costs record);

    List<Costs> CostsList();
}