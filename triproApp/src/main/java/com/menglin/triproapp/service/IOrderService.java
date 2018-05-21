package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.vo.OrederDetailVO;
import com.menglin.triproapp.vo.ResultOrderList;

/** 
 * @author CGS 
 * @date 2018年2月6日 上午10:54:32 
 */
public interface IOrderService {
	public Order get(String id);
	public void save(Order order);
	public void delete(String id);
	public void update(Order order);
	public Order findOrderByUid(Integer uid);
	public ResultOrderList selectOrderListByUid(Integer uid);
	public ResultOrderList selectOrderListByUidAndStatus(Integer uid,Integer state ,Integer receiveState);
	public OrederDetailVO findByOrederId(String orderId);
	PageBean<OrederDetailVO> findByPage(Integer currentPage,Integer pageSize,Order model,String orderId,String startTime,String endTime);
	List<OrederDetailVO> export(Order model,String orderId,String startTime,String endTime);
}
