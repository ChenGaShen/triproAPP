package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Adress;


public interface ActiveRedDao {
    int deleteByPrimaryKey(Integer activeid);

    int insert(ActiveRed record);

    int insertSelective(ActiveRed record);

    ActiveRed selectByPrimaryKey(Integer activeid);
    
    ActiveRed selectByorderId(String orderId); 

    int updateByPrimaryKeySelective(ActiveRed record);

    int updateByPrimaryKey(ActiveRed record);
    

    List<ActiveRed> selectActiveRedList(HashMap<String,Object> map);
    
    int selectActiveRedCount(HashMap<String,Object> map);
    
    
    int selectCount(HashMap<String,Object> map);
    
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<ActiveRed> findByPage(HashMap<String,Object> map);
}