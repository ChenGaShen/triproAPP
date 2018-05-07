package com.menglin.tripro.dao;

import java.util.List;

import com.menglin.tripro.entity.OrderItem;
import com.menglin.tripro.entity.Shopping;

public interface OrderItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
    
    List<OrderItem> selectListByOrderId(String  orderId);
}