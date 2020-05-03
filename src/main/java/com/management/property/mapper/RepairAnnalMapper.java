package com.management.property.mapper;

import com.management.property.pojo.ParkingPlant;
import com.management.property.pojo.RepairAnnal;

import java.util.List;

public interface RepairAnnalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepairAnnal record);

    int insertSelective(RepairAnnal record);

    RepairAnnal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RepairAnnal record);

    int updateByPrimaryKey(RepairAnnal record);

    List<RepairAnnal> selectAllByUserId(Integer limit, Integer page , Integer sid);

    List<RepairAnnal> selectAll(Integer limit, Integer page);

    int count();

    List<RepairAnnal> selectAllStatus(Integer limit, Integer page ,String status);
}