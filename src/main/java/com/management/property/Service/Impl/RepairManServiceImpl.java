package com.management.property.Service.Impl;

import com.management.property.Service.RepairManService;
import com.management.property.mapper.RepairManMapper;
import com.management.property.pojo.RepairMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairManServiceImpl implements RepairManService {
    @Autowired
    RepairManMapper repairManMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return repairManMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RepairMan record) {
        return repairManMapper.insert(record);
    }

    @Override
    public int insertSelective(RepairMan record) {
        return repairManMapper.insertSelective(record);
    }

    @Override
    public RepairMan selectByPrimaryKey(Integer id) {
        return repairManMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RepairMan record) {
        return repairManMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RepairMan record) {
        return repairManMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RepairMan> selectAll(Integer limit, Integer page) {
        return repairManMapper.selectAll(limit,page);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<RepairMan> selectAllByStatus(Integer limit, Integer page, String status) {
        return repairManMapper.selectAllByStatus(limit,page,status);
    }
}
