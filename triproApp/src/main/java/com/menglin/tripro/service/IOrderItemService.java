package com.menglin.tripro.service;

import java.util.List;

import com.menglin.tripro.entity.Order;
import com.menglin.tripro.entity.OrderItem;

/** 
 * @author CGS 
 * @date 2018年2月6日 下午2:04:54 
 */
public interface IOrderItemService {

	public OrderItem get(Integer id);
	public void save(OrderItem order);
	public void delete(Integer id);
	public void update(OrderItem order);
	List<OrderItem> findListByOrderId(String orderId);
}
