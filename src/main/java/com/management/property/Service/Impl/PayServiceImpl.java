package com.management.property.Service.Impl;

import com.management.property.Service.PayService;
import com.management.property.mapper.PayMapper;
import com.management.property.pojo.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    PayMapper payMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Pay record) {
        return payMapper.insert(record);
    }

    @Override
    public int insertSelective(Pay record) {
        return payMapper.insertSelective(record);
    }

    @Override
    public Pay selectByPrimaryKey(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Pay record) {
        return payMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Pay record) {
        return payMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Pay> selectAll(Integer limit, Integer page,String payWhether) {
        return payMapper.selectAll(limit,page,payWhether);
    }

    @Override
    public int count() {
        return payMapper.count();
    }

    @Override
    public List<Pay> selectAllByIdandWhether(Integer limit, Integer page, Integer id, String payWhether) {
        return payMapper.selectAllByIdandWhether(limit,page,id,payWhether);
    }

    @Override
    public List<Pay> selectByUserId(Integer userid,String pay) {
        return payMapper.selectByUserId(userid,pay);
    }

    @Override
    public List<Pay> UserIdSelect(Integer userid) {
        return payMapper.UserIdSelect(userid);
    }

}
