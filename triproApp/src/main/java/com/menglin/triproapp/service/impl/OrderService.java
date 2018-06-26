package com.menglin.triproapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Case;
import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.OrderDao;
import com.menglin.triproapp.dao.OrderItemDao;
import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.OrderItem;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.DateUntil;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.OrderItemVO;
import com.menglin.triproapp.vo.OrederDetailVO;
import com.menglin.triproapp.vo.ResultOrderList;

/** 
 * @author CGS 
 * @date 2018年2月6日 上午10:59:35 
 */
@Service("orderService")
public class OrderService implements IOrderService {

	@Resource
	private  OrderDao orderDao;
	
	@Resource
	private  OrderItemDao orderItemDao;
	
	@Override
	public Order get(String id) { 
		
		return orderDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(Order order) {
		orderDao.insertSelective(order);
	}

	@Override
	public void delete(String id) {
		orderDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Order order) {
		orderDao.updateByPrimaryKeySelective(order);

	}

	@Override
	public Order findOrderByUid(Integer uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 根据用户ID 查询所有订单List
	 */
	@Override
	public ResultOrderList selectOrderListByUid(Integer uid) {
		
		ResultOrderList resultOrderList =new ResultOrderList();
		
		List<OrederDetailVO> orederDetailVOs =new ArrayList<OrederDetailVO>();
		List<Order> orders= orderDao.selectByUid(uid);
	if (CheckData.isNotEmpty(orders)) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < orders.size(); i++) {
			OrederDetailVO detailVO=new OrederDetailVO();
			detailVO.setOrderId(orders.get(i).getId());
			
			List<OrderItem> items =orderItemDao.selectListByOrderId(orders.get(i).getId());
			List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
			for (int j = 0; j < items.size(); j++) {
				OrderItemVO itemVO=new OrderItemVO();
				itemVO.setOrderId(items.get(j).getOrderId());
				itemVO.setCommodityId(items.get(j).getCommodityId());
				itemVO.setCommodityName(items.get(j).getCommodityName());
				itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
				itemVO.setAmount(items.get(j).getAmount());
				itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
				itemVO.setSpecification(items.get(j).getSpecification());
				orderItemVOs.add(itemVO);
				
			}
			detailVO.setOrderItems(orderItemVOs);
			detailVO.setPayState(orders.get(i).getState());
			switch (orders.get(i).getState()){
			case 0:detailVO.setState("待付款");break;//状态：0待付款1已付款2取消订单3已失效
			case 1:detailVO.setState("已付款");break;
			case 2:detailVO.setState("已取消");break;
			case 3:detailVO.setState("已失效");break;
			}
			if (orders.get(i).getState()==1) {
				detailVO.setDeliveryState(orders.get(i).getReceiveState());
				switch (orders.get(i).getReceiveState()){
				case 0:detailVO.setReceiveState("待发货");break;//订单状态0待发货1配送中2已签收
				case 1:detailVO.setReceiveState("配送中");break;
				case 2:detailVO.setReceiveState("交易成功");break;
				}
			}
			if (CheckData.isNotNullOrEmpty(orders.get(i).getAir())) {
				detailVO.setAir(orders.get(i).getAir());
			}//运单号
			
			if (CheckData.isNotNullOrEmpty(orders.get(i).getCompany())) {
				detailVO.setCompany(orders.get(i).getCompany());
			}//物流公司
			detailVO.setOrderPrice(Format.keepTwoMoney(orders.get(i).getOrderPrice()));
			if (CheckData.isNotNullOrEmpty(orders.get(i).getRedMoney())) {
				detailVO.setRedMoney(Format.keepTwoMoney(orders.get(i).getRedMoney()));
			}else{
				detailVO.setRedMoney("0.00");
			}
			detailVO.setReceiveName(orders.get(i).getReceiveName());
			detailVO.setReceivePhone(orders.get(i).getReceivePhone());
			detailVO.setReceiveAddress(orders.get(i).getReceiveAddress());
			detailVO.setAddTime(sdf.format(orders.get(i).getAddTime()));
//			detailVO.setResult(Result.suc("订单数据!!"));
			orederDetailVOs.add(detailVO);
		}
		resultOrderList.setDetailVOs(orederDetailVOs);
	}	
	if (CheckData.isNotEmpty(orederDetailVOs)) {
		resultOrderList.setResult(Result.suc("全部订单查询成功!!"));
	}else{
		resultOrderList.setResult(Result.fal("全部订单为空~"));
	}
		return resultOrderList;
	}
	
	/**
	 * 根据订单ID 查询订单详情
	 */
	@Override
	public OrederDetailVO findByOrederId(String orderId) {
		OrederDetailVO detailVO=new OrederDetailVO();
		Order order= orderDao.selectByPrimaryKey(orderId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		detailVO.setOrderId(order.getId());
		
		List<OrderItem> items =orderItemDao.selectListByOrderId(orderId);
		List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
		for (int j = 0; j < items.size(); j++) {
			OrderItemVO itemVO=new OrderItemVO();
			itemVO.setOrderId(items.get(j).getOrderId());
			itemVO.setCommodityId(items.get(j).getCommodityId());
			itemVO.setCommodityName(items.get(j).getCommodityName());
			itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
			itemVO.setAmount(items.get(j).getAmount());
			itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
			itemVO.setSpecification(items.get(j).getSpecification());
			orderItemVOs.add(itemVO);
			
		}
		detailVO.setOrderItems(orderItemVOs);
		detailVO.setPayState(order.getState());
		switch (order.getState()){
		case 0:detailVO.setState("待付款");break;//状态：0待付款1已付款2取消订单3已失效
		case 1:detailVO.setState("已付款");break;
		case 2:detailVO.setState("已取消");break;
		case 3:detailVO.setState("已失效");break;
		}
		if (order.getState()==1) {
			detailVO.setDeliveryState(order.getReceiveState());
			switch (order.getReceiveState()){
			case 0:detailVO.setReceiveState("待发货");break;//订单状态0待发货1配送中2已签收
			case 1:detailVO.setReceiveState("配送中");break;
			case 2:detailVO.setReceiveState("交易成功");break;
			}
		}
		if (CheckData.isNotNullOrEmpty(order.getAir())) {
			detailVO.setAir(order.getAir());
		}//运单号
		
		if (CheckData.isNotNullOrEmpty(order.getCompany())) {
			detailVO.setCompany(order.getCompany());
		}//物流公司
		detailVO.setLoginName(order.getLoginName());//下单人手机号
		detailVO.setRemark(order.getRemark());//订单备注
		detailVO.setOrderPrice(Format.keepTwoMoney(order.getOrderPrice()));
		if (CheckData.isNotNullOrEmpty(order.getRedMoney())) {
			detailVO.setRedMoney(Format.keepTwoMoney(order.getRedMoney()));
		}else{
			detailVO.setRedMoney("0.00");
		}
		detailVO.setReceiveName(order.getReceiveName());
		detailVO.setReceivePhone(order.getReceivePhone());
		detailVO.setReceiveAddress(order.getReceiveAddress());
		detailVO.setAddTime(sdf.format(order.getAddTime()));
		return detailVO;
	}
	
	/**
	 * 根据用户ID 和订单支付状态+收货状态查询订单List
	 */
	@Override
	public ResultOrderList selectOrderListByUidAndStatus(Integer uid, Integer state, Integer receiveState) {
	ResultOrderList resultOrderList =new ResultOrderList();
		
		List<OrederDetailVO> orederDetailVOs =new ArrayList<OrederDetailVO>();
		//封装订单查询参数
		HashMap<String,Object> map = new HashMap<String,Object>();
		 	map.put("uid",uid);
	        map.put("state", state);
	        map.put("receiveState",receiveState);
	        
		List<Order> orders= orderDao.selectByUidAndStatus(map);
	if (CheckData.isNotEmpty(orders)) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < orders.size(); i++) {
			OrederDetailVO detailVO=new OrederDetailVO();
			detailVO.setOrderId(orders.get(i).getId());
			
			List<OrderItem> items =orderItemDao.selectListByOrderId(orders.get(i).getId());
			List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
			for (int j = 0; j < items.size(); j++) {
				OrderItemVO itemVO=new OrderItemVO();
				itemVO.setOrderId(items.get(j).getOrderId());
				itemVO.setCommodityId(items.get(j).getCommodityId());
				itemVO.setCommodityName(items.get(j).getCommodityName());
				itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
				itemVO.setAmount(items.get(j).getAmount());
				itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
				itemVO.setSpecification(items.get(j).getSpecification());
				orderItemVOs.add(itemVO);
				
			}
			detailVO.setOrderItems(orderItemVOs);
			detailVO.setPayState(orders.get(i).getState());
			switch (orders.get(i).getState()){
			case 0:detailVO.setState("待付款");break;//状态：0待付款1已付款2取消订单3已失效
			case 1:detailVO.setState("已付款");break;
			case 2:detailVO.setState("已取消");break;
			case 3:detailVO.setState("已失效");break;
			}
			if (orders.get(i).getState()==1) {
				detailVO.setDeliveryState(orders.get(i).getReceiveState());
				switch (orders.get(i).getReceiveState()){
				case 0:detailVO.setReceiveState("待发货");break;//订单状态0待发货1配送中2已签收
				case 1:detailVO.setReceiveState("配送中");break;
				case 2:detailVO.setReceiveState("交易成功");break;
				}
			}
			if (CheckData.isNotNullOrEmpty(orders.get(i).getAir())) {
				detailVO.setAir(orders.get(i).getAir());
			}//运单号
			
			if (CheckData.isNotNullOrEmpty(orders.get(i).getCompany())) {
				detailVO.setCompany(orders.get(i).getCompany());
			}//物流公司
			detailVO.setOrderPrice(Format.keepTwoMoney(orders.get(i).getOrderPrice()));
			if (CheckData.isNotNullOrEmpty(orders.get(i).getRedMoney())) {
				detailVO.setRedMoney(Format.keepTwoMoney(orders.get(i).getRedMoney()));
			}else{
				detailVO.setRedMoney("0.00");
			}
			detailVO.setReceiveName(orders.get(i).getReceiveName());
			detailVO.setReceivePhone(orders.get(i).getReceivePhone());
			detailVO.setReceiveAddress(orders.get(i).getReceiveAddress());
			detailVO.setAddTime(sdf.format(orders.get(i).getAddTime()));
//			detailVO.setResult(Result.suc("主订单数据!!"));
			orederDetailVOs.add(detailVO);
		}
		resultOrderList.setDetailVOs(orederDetailVOs);
	}	
	
	if (CheckData.isNotEmpty(orederDetailVOs)) {
		resultOrderList.setResult(Result.suc("不同状态订单列表查询成功!!"));
	}else{
		resultOrderList.setResult(Result.fal("订单空空如也~"));
	}
		
		return resultOrderList;
	}
	
	@Override
	public PageBean<OrederDetailVO> findByPage(Integer currentPage, Integer pageSize, Order model,String orderId, String startTime,String endTime) {
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
       
        map1.put("state", model.getState());
        map1.put("receiveState", model.getReceiveState());
        map1.put("orderId", orderId);
        map1.put("loginName", model.getLoginName());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = orderDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
    	
    	map.put("state", model.getState());
        map.put("receiveState", model.getReceiveState());
        map.put("orderId", orderId);
        map.put("loginName", model.getLoginName());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
       
        List<Order> lists = orderDao.findByPage(map);
        // 循环遍历出订单所有内容
        List<OrederDetailVO> orederDetailVOs =new ArrayList<OrederDetailVO>();
        if (CheckData.isNotEmpty(lists)) {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		for (int i = 0; i < lists.size(); i++) {
    			OrederDetailVO detailVO=new OrederDetailVO();
    			detailVO.setOrderId(lists.get(i).getId());
    			List<OrderItem> items =orderItemDao.selectListByOrderId(lists.get(i).getId());
    			List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
    			for (int j = 0; j < items.size(); j++) {
    				OrderItemVO itemVO=new OrderItemVO();
    				itemVO.setOrderId(items.get(j).getOrderId());
    				itemVO.setCommodityId(items.get(j).getCommodityId());
    				itemVO.setCommodityName(items.get(j).getCommodityName());
    				itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
    				itemVO.setAmount(items.get(j).getAmount());
    				itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
    				itemVO.setSpecification(items.get(j).getSpecification());
    				orderItemVOs.add(itemVO);
    				
    			}
    			detailVO.setOrderItems(orderItemVOs);
    			detailVO.setState(lists.get(i).getState().toString());
    			if (lists.get(i).getState()==1) {
    				detailVO.setReceiveState(lists.get(i).getReceiveState().toString());
    			}
    			detailVO.setSeckillState(lists.get(i).getSeckillState().toString());// 订单秒杀状态0秒杀订单1普通订单
    			/*switch (lists.get(i).getState()){
    			case 0:detailVO.setState("待付款");break;//状态：0待付款1已付款2取消订单3已失效
    			case 1:detailVO.setState("已付款");break;
    			case 2:detailVO.setState("已取消");break;
    			case 3:detailVO.setState("已失效");break;
    			}*/
    		/*	if (lists.get(i).getState()==1) {
    				switch (lists.get(i).getReceiveState()){
    				case 0:detailVO.setReceiveState("待发货");break;//订单状态0待发货1配送中2已签收
    				case 1:detailVO.setReceiveState("配送中");break;
    				case 2:detailVO.setReceiveState("交易成功");break;
    				}
    			}*/
    			if (CheckData.isNotNullOrEmpty(lists.get(i).getAir())) {
    				detailVO.setAir(lists.get(i).getAir());
    			}//运单号
    			
    			if (CheckData.isNotNullOrEmpty(lists.get(i).getCompany())) {
    				detailVO.setCompany(lists.get(i).getCompany());
    			}//物流公司
    			detailVO.setLoginName(lists.get(i).getLoginName());//下单人微信昵称
    			detailVO.setRemark(lists.get(i).getRemark());//订单备注
    			detailVO.setOrderPrice(Format.keepTwoMoney(lists.get(i).getOrderPrice()));
    			if (CheckData.isNotNullOrEmpty(lists.get(i).getRedMoney())) {
    				detailVO.setRedMoney(Format.keepTwoMoney(lists.get(i).getRedMoney()));
    			}else{
    				detailVO.setRedMoney("0.00");
    			}
    			detailVO.setReceiveName(lists.get(i).getReceiveName());
    			detailVO.setReceivePhone(lists.get(i).getReceivePhone());
    			detailVO.setReceiveAddress(lists.get(i).getReceiveAddress());
    			if (CheckData.isNotNullOrEmpty(lists.get(i).getPayTime())) {
    				detailVO.setPayTime(sdf.format(lists.get(i).getPayTime()));
				}
    			detailVO.setAddTime(sdf.format(lists.get(i).getAddTime()));
//    			detailVO.setResult(Result.suc("订单数据!!"));
    			orederDetailVOs.add(detailVO);
    		}
    	}
        
        PageBean<OrederDetailVO> pageBean = new PageBean<OrederDetailVO>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(orederDetailVOs);

        return pageBean;
	}
	
	//数据导出
	@Override
	public List<OrederDetailVO> export(Order model, String orderId, String startTime, String endTime) {
		
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		
		
		   //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
    	map.put("state", model.getState());
        map.put("receiveState", model.getReceiveState());
        map.put("orderId", orderId);
        map.put("loginName", model.getLoginName());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("start",null);
        map.put("size", null);
		  List<Order> lists = orderDao.findByPage(map);
	        // 循环遍历出订单所有内容
	        List<OrederDetailVO> orederDetailVOs =new ArrayList<OrederDetailVO>();
	        if (CheckData.isNotEmpty(lists)) {
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		for (int i = 0; i < lists.size(); i++) {
	    			OrederDetailVO detailVO=new OrederDetailVO();
	    			detailVO.setOrderId(lists.get(i).getId());
	    			List<OrderItem> items =orderItemDao.selectListByOrderId(lists.get(i).getId());
	    			List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
	    			for (int j = 0; j < items.size(); j++) {
	    				OrderItemVO itemVO=new OrderItemVO();
	    				itemVO.setOrderId(items.get(j).getOrderId());
	    				itemVO.setCommodityId(items.get(j).getCommodityId());
	    				itemVO.setCommodityName(items.get(j).getCommodityName());
	    				itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
	    				itemVO.setAmount(items.get(j).getAmount());
	    				itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
	    				itemVO.setSpecification(items.get(j).getSpecification());
	    				orderItemVOs.add(itemVO);
	    				
	    			}
	    			detailVO.setOrderItems(orderItemVOs);
	    			detailVO.setState(lists.get(i).getState().toString());//状态：0待付款1已付款2取消订单3已失效
	    			if (lists.get(i).getState()==1) {
	    				detailVO.setReceiveState(lists.get(i).getReceiveState().toString());//订单状态0待发货1配送中2已签收
	    			}
	    			detailVO.setSeckillState(lists.get(i).getSeckillState().toString()); // 订单秒杀状态0秒杀订单1普通订单
	    			if (CheckData.isNotNullOrEmpty(lists.get(i).getAir())) {
	    				detailVO.setAir(lists.get(i).getAir());
	    			}//运单号
	    			if (CheckData.isNotNullOrEmpty(lists.get(i).getCompany())) {
	    				detailVO.setCompany(lists.get(i).getCompany());
	    			}//物流公司
	    			detailVO.setLoginName(lists.get(i).getLoginName());//下单人昵称
	    			detailVO.setRemark(lists.get(i).getRemark());//订单备注
	    			detailVO.setOrderPrice(Format.keepTwoMoney(lists.get(i).getOrderPrice()));
	    			detailVO.setRedMoney(Format.keepTwoMoney(lists.get(i).getRedMoney()));
	    			detailVO.setReceiveName(lists.get(i).getReceiveName());
	    			detailVO.setReceivePhone(lists.get(i).getReceivePhone());
	    			detailVO.setReceiveAddress(lists.get(i).getReceiveAddress());
	    			if (CheckData.isNotNullOrEmpty(lists.get(i).getPayTime())) {
	    				detailVO.setPayTime(sdf.format(lists.get(i).getPayTime()));
					}
	    			detailVO.setAddTime(sdf.format(lists.get(i).getAddTime()));
//	    			detailVO.setResult(Result.suc("订单数据!!"));
	    			orederDetailVOs.add(detailVO);
	    		}
	    	}
		return orederDetailVOs;
	}

	/**
	 * 小程序 订单  字段 -模糊查询
	 */
	@Override
	public ResultOrderList selectOrderListByfield(Integer uid, String field) {
		ResultOrderList resultOrderList =new ResultOrderList();
		List<OrederDetailVO> orederDetailVOs =new ArrayList<OrederDetailVO>();
		//封装订单查询参数
		HashMap<String,Object> map = new HashMap<String,Object>();
		 	map.put("uid",uid);
	        map.put("field", field);
		List<Order> orders= orderDao.selectByUidAndFiled(map);
	if (CheckData.isNotEmpty(orders)) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < orders.size(); i++) {
			OrederDetailVO detailVO=new OrederDetailVO();
			detailVO.setOrderId(orders.get(i).getId());
			
			List<OrderItem> items =orderItemDao.selectListByOrderId(orders.get(i).getId());
			List<OrderItemVO> orderItemVOs =new ArrayList<OrderItemVO>();
			for (int j = 0; j < items.size(); j++) {
				OrderItemVO itemVO=new OrderItemVO();
				itemVO.setOrderId(items.get(j).getOrderId());
				itemVO.setCommodityId(items.get(j).getCommodityId());
				itemVO.setCommodityName(items.get(j).getCommodityName());
				itemVO.setImg(SystemParam.DOMAIN_NAME+items.get(j).getImg());
				itemVO.setAmount(items.get(j).getAmount());
				itemVO.setPrice(Format.keepTwoMoney(items.get(j).getPrice()));
				itemVO.setSpecification(items.get(j).getSpecification());
				orderItemVOs.add(itemVO);
				
			}
			detailVO.setOrderItems(orderItemVOs);
			detailVO.setPayState(orders.get(i).getState());
			switch (orders.get(i).getState()){
			case 0:detailVO.setState("待付款");break;//状态：0待付款1已付款2取消订单3已失效
			case 1:detailVO.setState("已付款");break;
			case 2:detailVO.setState("已取消");break;
			case 3:detailVO.setState("已失效");break;
			}
			if (orders.get(i).getState()==1) {
				detailVO.setDeliveryState(orders.get(i).getReceiveState());
				switch (orders.get(i).getReceiveState()){
				case 0:detailVO.setReceiveState("待发货");break;//订单状态0待发货1配送中2已签收
				case 1:detailVO.setReceiveState("配送中");break;
				case 2:detailVO.setReceiveState("交易成功");break;
				}
			}
			if (CheckData.isNotNullOrEmpty(orders.get(i).getAir())) {
				detailVO.setAir(orders.get(i).getAir());
			}//运单号
			
			if (CheckData.isNotNullOrEmpty(orders.get(i).getCompany())) {
				detailVO.setCompany(orders.get(i).getCompany());
			}//物流公司
			detailVO.setOrderPrice(Format.keepTwoMoney(orders.get(i).getOrderPrice()));
			if (CheckData.isNotNullOrEmpty(orders.get(i).getRedMoney())) {
				detailVO.setRedMoney(Format.keepTwoMoney(orders.get(i).getRedMoney()));
			}else{
				detailVO.setRedMoney("0.00");
			}
			detailVO.setReceiveName(orders.get(i).getReceiveName());
			detailVO.setReceivePhone(orders.get(i).getReceivePhone());
			detailVO.setReceiveAddress(orders.get(i).getReceiveAddress());
			detailVO.setAddTime(sdf.format(orders.get(i).getAddTime()));
//			detailVO.setResult(Result.suc("主订单数据!!"));
			orederDetailVOs.add(detailVO);
		}
		resultOrderList.setDetailVOs(orederDetailVOs);
	}	
	
	if (CheckData.isNotEmpty(orederDetailVOs)) {
		resultOrderList.setResult(Result.suc("模糊搜索订单列表查询成功!!"));
	}else{
		resultOrderList.setResult(Result.fal("订单空空如也~"));
	}
		
		return resultOrderList;
	}
	
	

}
