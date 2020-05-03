package com.management.property.Service.Impl;

import com.management.property.Service.RepairAnnalService;
import com.management.property.mapper.RepairAnnalMapper;
import com.management.property.pojo.RepairAnnal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairAnnalServiceImpl implements RepairAnnalService {
    @Autowired
    RepairAnnalMapper repairAnnalMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return repairAnnalMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RepairAnnal record) {
        return repairAnnalMapper.insert(record);
    }

    @Override
    public int insertSelective(RepairAnnal record) {
        return repairAnnalMapper.insertSelective(record);
    }

    @Override
    public RepairAnnal selectByPrimaryKey(Integer id) {
        return repairAnnalMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RepairAnnal record) {
        return repairAnnalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RepairAnnal record) {
        return repairAnnalMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RepairAnnal> selectAllByUserId(Integer limit, Integer page, Integer sid) {
        return repairAnnalMapper.selectAllByUserId(limit,page,sid);
    }

    @Override
    public List<RepairAnnal> selectAll(Integer limit, Integer page) {
        return repairAnnalMapper.selectAll(limit,page);
    }

    @Override
    public int count() {
        return repairAnnalMapper.count();
    }

    @Override
    public List<RepairAnnal> selectAllStatus(Integer limit, Integer page, String status) {
        return repairAnnalMapper.selectAllStatus(limit,page,status);
    }
}
