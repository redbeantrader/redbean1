package com.management.property.shiro.service;

import com.management.property.shiro.pojo.Role;

public interface RoleService {

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRoleByLoginName(String LogiName);

}
