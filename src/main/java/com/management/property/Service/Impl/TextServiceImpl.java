package com.management.property.Service.Impl;

import com.management.property.Service.TextService;
import com.management.property.mapper.TextMapper;
import com.management.property.pojo.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    TextMapper textMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return textMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Text record) {
        return textMapper.insert(record);
    }

    @Override
    public int insertSelective(Text record) {
        return textMapper.insertSelective(record);
    }

    @Override
    public Text selectByPrimaryKey(Integer id) {
        return textMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Text record) {
        return textMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Text record) {
        return textMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Text> selectAll(Integer limit, Integer page) {
        return textMapper.selectAll(limit,page);
    }

    @Override
    public int count() {
        return textMapper.count();
    }
}
