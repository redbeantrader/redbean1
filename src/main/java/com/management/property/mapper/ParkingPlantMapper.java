package com.management.property.mapper;

import com.management.property.pojo.ParkingPlant;
import com.management.property.pojo.TypeInformation;

import java.util.List;

public interface ParkingPlantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkingPlant record);

    int insertSelective(ParkingPlant record);

    ParkingPlant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkingPlant record);

    int updateByPrimaryKey(ParkingPlant record);

    List<ParkingPlant> selectAllByParking(Integer limit, Integer page ,Integer parid);

    List<ParkingPlant> selectAll(Integer limit, Integer page);

    int count();

    List<ParkingPlant> selectAllcom(Integer limit, Integer page ,String Sell);

    List<ParkingPlant> selectByRegion(String region);
}