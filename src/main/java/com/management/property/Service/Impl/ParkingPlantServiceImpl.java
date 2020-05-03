package com.management.property.Service.Impl;

import com.management.property.Service.ParkingPlantService;
import com.management.property.mapper.ParkingPlantMapper;
import com.management.property.pojo.ParkingPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingPlantServiceImpl implements ParkingPlantService {
    @Autowired
    ParkingPlantMapper parkingPlantMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return parkingPlantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ParkingPlant record) {
        return parkingPlantMapper.insert(record);
    }

    @Override
    public int insertSelective(ParkingPlant record) {
        return parkingPlantMapper.insertSelective(record);
    }

    @Override
    public ParkingPlant selectByPrimaryKey(Integer id) {
        return parkingPlantMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ParkingPlant record) {
        return parkingPlantMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ParkingPlant record) {
        return parkingPlantMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ParkingPlant> selectAllByParking(Integer limit, Integer page,Integer parid) {
        return parkingPlantMapper.selectAllByParking(limit,page,parid);
    }

    @Override
    public List<ParkingPlant> selectAll(Integer limit, Integer page) {
        return parkingPlantMapper.selectAll(limit,page);
    }

    @Override
    public int count() {
        return parkingPlantMapper.count();
    }

    @Override
    public List<ParkingPlant> selectAllcom(Integer limit, Integer page, String Sell) {
        return parkingPlantMapper.selectAllcom(limit,page,Sell);
    }

    @Override
    public List<ParkingPlant> selectByRegion(String region) {
        return parkingPlantMapper.selectByRegion(region);
    }
}
