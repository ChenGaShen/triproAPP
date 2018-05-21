package com.menglin.triproapp.dao;

import java.util.List;

import com.menglin.triproapp.entity.OrderItem;
import com.menglin.triproapp.entity.Shopping;

public interface OrderItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
    
    List<OrderItem> selectListByOrderId(String  orderId);
}