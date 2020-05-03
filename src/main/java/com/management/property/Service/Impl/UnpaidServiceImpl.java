package com.management.property.Service.Impl;

import com.management.property.Service.UnpaidService;
import com.management.property.mapper.UnpaidMapper;
import com.management.property.pojo.Unpaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnpaidServiceImpl implements UnpaidService {
    @Autowired
    UnpaidMapper unpaidMapper;

    @Override
    public int deleteByPrimaryKey(Integer unpaidId) {
        return unpaidMapper.deleteByPrimaryKey(unpaidId);
    }

    @Override
    public int insert(Unpaid record) {
        return unpaidMapper.insert(record);
    }

    @Override
    public int insertSelective(Unpaid record) {
        return unpaidMapper.insertSelective(record);
    }

    @Override
    public Unpaid selectByPrimaryKey(Integer unpaidId) {
        return unpaidMapper.selectByPrimaryKey(unpaidId);
    }

    @Override
    public int updateByPrimaryKeySelective(Unpaid record) {
        return unpaidMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Unpaid record) {
        return unpaidMapper.updateByPrimaryKey(record);
    }

    @Override
    public int count() {
        return unpaidMapper.count();
    }

    @Override
    public List<Unpaid> selectAll(Integer limit, Integer page) {
        return unpaidMapper.selectAll(limit,page);
    }

    @Override
    public List<Unpaid> All() {
        return unpaidMapper.All();
    }

    @Override
    public Unpaid SelectByUserId(Integer userid) {
        return unpaidMapper.SelectByUserId(userid);
    }
}
