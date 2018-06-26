package com.menglin.triproapp.util.pay.weixin;


import java.util.Date;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.service.IPayWxService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;


/** 
 * @author CGS 
 * @date 2018年3月14日 下午5:21:36 
 */
public class PayOrderThread extends Thread {
	
	
    private IUserService userService;
	
	
    private IOrderService orderService;
	
	private  IPayWxService payWxService;
	
	 
    private IMessageService messageService;
    
    private IActiveRedService activeRedService;
	
	private String out_trade_no ="";
	private String total_fee ="";
	private String transaction_id ="";
	private String bank_type ="";
	private String time_end ="";
	private Date date;
	private Order order;
	
	
	public PayOrderThread(Order order ,Date date,IUserService userService,IOrderService orderService,IPayWxService payWxService,IMessageService messageService,IActiveRedService activeRedService,String out_trade_no, String total_fee, String transaction_id, String bank_type,
			String time_end) {
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.transaction_id = transaction_id;
		this.bank_type = bank_type;
		this.time_end = time_end;
		this.date=date;
		this.order=order;
		this.userService =userService;
		this.orderService =orderService;
		this.payWxService =payWxService;
		this.messageService =messageService;
		this.activeRedService =activeRedService;
		
	}


	public void run() {
		System.out.println("进入run方法了.....");
		String timeMoney=WeixinPayUtil.changeF2Y(total_fee);//分转变为元
		Double payMoney= Double.parseDouble(timeMoney);
		System.out.println(payMoney);
		order.setMoney(payMoney);//支付的金额
		order.setPayTime(date);//时间戳转化为date 支付时间
		order.setState(1);//订单状态改为已付款： 1  //支付状态：0待付款1已付款2取消订单3已失效
		order.setReceiveState(0); //收货状态0待发货1配送中2已签收
		orderService.update(order);
		
		System.out.println("微信记录添加开始*********");
		 //添加微信支付记录
		PayWx payWx =new PayWx();
		payWx.setUid(order.getUid());//下单的用户ID
		payWx.setOutTradeNo(out_trade_no);//商户订单号
		payWx.setTotalFee(Integer.valueOf(total_fee));
		payWx.setCashFee(payMoney);//分变元，添加记录
		payWx.setTransactionId(transaction_id);//微信订单号
		payWx.setBankType(bank_type);
		payWx.setTimeEnd(time_end);
		payWx.setAddTime(new Date());
		payWxService.save(payWx);
		System.out.println("微信记录结束********");
		
		//用户添加支付成功消息记录
		System.out.println("消息记录添加开始---------");
		Message userMessage =new Message();
		userMessage.setUid(order.getUid());
		userMessage.setOrderId(out_trade_no);//订单号
		userMessage.setMoney(payMoney);
		userMessage.setTitle("支付成功");
		userMessage.setContent("您已于  "+Format.formatDateTime(order.getPayTime())+" 时支付成功!!");
		userMessage.setState(0);//状态：0未读1已读2跟进
		userMessage.setType(0); //消息类型 0私有 1公共 2订单
		userMessage.setAddTime(new Date());
		messageService.save(userMessage);
		
		//系统添加支付消息记录
		Message orderMessage =new Message();
		orderMessage.setUid(order.getUid());
		orderMessage.setOrderId(out_trade_no);//订单号
		orderMessage.setMoney(payMoney);
		orderMessage.setTitle("订单处理");
		orderMessage.setContent("用户于  "+Format.formatDateTime(order.getPayTime())+" 时支付下单，请及时处理!!");
		orderMessage.setState(0);//状态：0未读1已读2跟进
		orderMessage.setType(2); //消息类型 0私有 1公共 2订单
		orderMessage.setAddTime(new Date());
		messageService.save(orderMessage);
		System.out.println("消息记录结束---------");
		
		if (CheckData.isNotNullOrEmpty(order.getRedMoney())&& order.getRedMoney()!=0.00) {
			//红包状态修改记录
			System.out.println("红包状态修改记录开始---------");
			ActiveRed  activeRed=activeRedService.selectByorderId(out_trade_no);
			activeRed.setRedState(1);//红包状态修改 //0 未使用1 已使用 2已过期
			activeRedService.update(activeRed);
			System.out.println("红包状态修改记录结束---------");
		}
		
	}
}
