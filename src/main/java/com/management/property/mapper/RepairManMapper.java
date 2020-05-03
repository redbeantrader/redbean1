package com.management.property.mapper;

import com.management.property.pojo.RepairAnnal;
import com.management.property.pojo.RepairMan;

import java.util.List;

public interface RepairManMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepairMan record);

    int insertSelective(RepairMan record);

    RepairMan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RepairMan record);

    int updateByPrimaryKey(RepairMan record);

    List<RepairMan> selectAll(Integer limit, Integer page);

    int count();

    List<RepairMan> selectAllByStatus(Integer limit, Integer page,String status);
}