package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.triproapp.entity.Home;

public interface HomeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
    
    int selectCount(HashMap<String,Object> map);
    
    List<Home> findByPage(HashMap<String,Object> map);
    
    List<Home> selectHomeList(HashMap<String,Object> map);
}