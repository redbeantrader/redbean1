package com.management.property.Service.Impl;

import com.management.property.Service.CostsService;
import com.management.property.mapper.CostsMapper;
import com.management.property.pojo.Costs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostsServiceImpl implements CostsService {
    @Autowired
    CostsMapper costsMapper;

    @Override
    public int deleteByPrimaryKey(Integer costsId) {
        return costsMapper.deleteByPrimaryKey(costsId);
    }

    @Override
    public int insert(Costs record) {
        return costsMapper.insert(record);
    }

    @Override
    public int insertSelective(Costs record) {
        return costsMapper.insertSelective(record);
    }

    @Override
    public Costs selectByPrimaryKey(Integer costsId) {
        return costsMapper.selectByPrimaryKey(costsId);
    }

    @Override
    public int updateByPrimaryKeySelective(Costs record) {
        return costsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Costs record) {
        return costsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Costs> CostsList() {
        return costsMapper.CostsList();
    }
}
