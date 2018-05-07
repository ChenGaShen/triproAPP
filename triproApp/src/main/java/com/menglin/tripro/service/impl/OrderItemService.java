package com.menglin.tripro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.tripro.dao.OrderItemDao;
import com.menglin.tripro.entity.Order;
import com.menglin.tripro.entity.OrderItem;
import com.menglin.tripro.service.IOrderItemService;

/** 
 * @author CGS 
 * @date 2018年2月6日 下午2:05:28 
 */
@Service("orderItemService")
public class OrderItemService implements IOrderItemService {

	
	@Resource
	private OrderItemDao orderItemDao;
	
	@Override
	public OrderItem get(Integer id) {
		
		return orderItemDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(OrderItem orderItem) {
		orderItemDao.insertSelective(orderItem);
	}

	@Override
	public void delete(Integer id) {
		orderItemDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemDao.updateByPrimaryKeySelective(orderItem);

	}

	@Override
	public List<OrderItem> findListByOrderId(String orderId) {
		
		return orderItemDao.selectListByOrderId(orderId);
	}
	
	

}
