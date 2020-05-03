package com.management.property.shiro.mapper;

import com.management.property.shiro.pojo.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    UserRole selectById(Integer userId);
}