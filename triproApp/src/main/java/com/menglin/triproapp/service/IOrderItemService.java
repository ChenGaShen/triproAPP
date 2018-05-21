package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.OrderItem;

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
