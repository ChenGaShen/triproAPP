package com.menglin.triproapp.vo;

import java.util.List;

import com.menglin.triproapp.entity.OrderItem;
import com.menglin.triproapp.util.Result;

/** 
 * @author CGS 
 * @date 2018年2月7日 上午9:57:19 
 */
public class OrederDetailVO {

	private Result result; 
	
	private String orderId;
	
	private List<OrderItemVO> orderItems;//订单子项
	
	private String state; //支付状态String：0待付款1已付款2取消订单3已失效
	
	private Integer payState; //支付状态int：0待付款1已付款2取消订单3已失效
	
	private Integer deliveryState; // 订单状态int :0待发货1配送中2已签收
	
	private String receiveState;// 订单状态String :0待发货1配送中2已签收
	
	private String receiveName;

	private String receivePhone;

	private String receiveAddress;
	
	private String air;

	private String company;

	private String addTime;
	
	private String payTime;
	
	private String remark;
	
	private String orderPrice; //实付金额
	
	private String redMoney; //红包金额
	
    private String loginName; //下单人昵称

		

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	public List<OrderItemVO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemVO> orderItems) {
		this.orderItems = orderItems;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Integer getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(Integer deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}
	
	
	
	public String getAir() {
		return air;
	}

	public void setAir(String air) {
		this.air = air;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(String redMoney) {
		this.redMoney = redMoney;
	}

	
	
	
}
