package com.management.property.shiro.service;

import com.management.property.shiro.pojo.User;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByLoginName(String LoginName);

    List<User> ALL();

}
