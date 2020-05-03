package com.management.property.Service;

import com.management.property.pojo.TypeInformation;

import java.util.List;

public interface TypeInformationService {

    int deleteByPrimaryKey(Integer id);

    int insert(TypeInformation record);

    int insertSelective(TypeInformation record);

    TypeInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TypeInformation record);

    int updateByPrimaryKey(TypeInformation record);

    List<TypeInformation> selectAll(Integer limit, Integer page);

    List<TypeInformation> selectAllStr(Integer limit, Integer page,String Sell);

    int count();

    List<TypeInformation> selectByRegion(String region);
}
