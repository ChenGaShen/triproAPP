package com.menglin.invest.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.invest.entity.Information;

public interface InformationDao
{
    int deleteByPrimaryKey(Integer newsId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);
    
    int selectCount(HashMap<String,Object> map);
    
    List<Information> findByPage(HashMap<String,Object> map);
}