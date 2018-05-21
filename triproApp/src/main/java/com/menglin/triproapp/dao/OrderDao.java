package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.Order;

public interface OrderDao {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectByUid(Integer uid);
    
    List<Order> selectByUidAndStatus(HashMap<String,Object> map);
    
    
    /**
     * 查询用户记录总数(条件)
     * @return
     */
    int selectCount(HashMap<String,Object> map);
    
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<Order> findByPage(HashMap<String,Object> map);
    
}