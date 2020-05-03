package com.management.property.Service.Impl;

import com.management.property.Service.TypeInformationService;
import com.management.property.mapper.TypeInformationMapper;
import com.management.property.pojo.TypeInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeInformationImpl implements TypeInformationService {
    @Autowired
    TypeInformationMapper typeInformationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return typeInformationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TypeInformation record) {
        return typeInformationMapper.insert(record);
    }

    @Override
    public int insertSelective(TypeInformation record) {
        return typeInformationMapper.insertSelective(record);
    }

    @Override
    public TypeInformation selectByPrimaryKey(Integer id) {
        return typeInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TypeInformation record) {
        return typeInformationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TypeInformation record) {
        return typeInformationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TypeInformation> selectAll(Integer limit, Integer page) {
        return typeInformationMapper.selectAll(limit,page);
    }

    @Override
    public List<TypeInformation> selectAllStr(Integer limit, Integer page ,String Sell) {
        return typeInformationMapper.selectAllStr(limit,page,Sell);
    }

    @Override
    public int count() {
        return typeInformationMapper.count();
    }

    @Override
    public List<TypeInformation> selectByRegion(String region) {
        return typeInformationMapper.selectByRegion(region);
    }


}
