package com.management.property.Service;

import com.management.property.pojo.Pay;

import java.util.List;

public interface PayService {
    int deleteByPrimaryKey(Integer id);

    int insert(Pay record);

    int insertSelective(Pay record);

    Pay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);

    List<Pay> selectAll(Integer limit, Integer page,String payWhether);

    int count();

    List<Pay> selectAllByIdandWhether(Integer limit, Integer page,Integer id,String payWhether);

    List<Pay> selectByUserId(Integer userid,String pay);

    List<Pay> UserIdSelect(Integer userid);

}
