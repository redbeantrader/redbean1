package com.management.property.mapper;

import com.management.property.pojo.MessageCenter;
import com.management.property.pojo.ParkingPlant;

import java.util.List;

public interface MessageCenterMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageCenter record);

    int insertSelective(MessageCenter record);

    MessageCenter selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(MessageCenter record);

    int updateByPrimaryKey(MessageCenter record);

    List<MessageCenter> selectAll(Integer limit, Integer page,Integer id);

    int count();
}