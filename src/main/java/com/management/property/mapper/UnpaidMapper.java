package com.management.property.mapper;

import com.management.property.pojo.Unpaid;

import java.util.List;

public interface UnpaidMapper {
    int deleteByPrimaryKey(Integer unpaidId);

    int insert(Unpaid record);

    int insertSelective(Unpaid record);

    Unpaid selectByPrimaryKey(Integer unpaidId);

    int updateByPrimaryKeySelective(Unpaid record);

    int updateByPrimaryKey(Unpaid record);

    int count();

    List<Unpaid> selectAll(Integer limit, Integer page);

    List<Unpaid> All();

    Unpaid SelectByUserId(Integer userid);
}