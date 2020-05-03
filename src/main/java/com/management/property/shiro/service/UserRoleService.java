package com.management.property.shiro.service;

import com.management.property.shiro.pojo.UserRole;

public interface UserRoleService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    UserRole selectById(Integer userId);

}
