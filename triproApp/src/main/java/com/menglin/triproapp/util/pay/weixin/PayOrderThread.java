package com.menglin.triproapp.util.pay.weixin;


import java.util.Date;

import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.service.IPayWxService;
import com.menglin.triproapp.service.IUserService;
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
	
	private String out_trade_no ="";
	private String total_fee ="";
	private String transaction_id ="";
	private String bank_type ="";
	private String time_end ="";
	private Date date;
	private Order order;
	
	
	public PayOrderThread(Order order ,Date date,IUserService userService,IOrderService orderService,IPayWxService payWxService,IMessageService messageService,String out_trade_no, String total_fee, String transaction_id, String bank_type,
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
		
		//添加消息记录
		System.out.println("消息记录添加开始---------");
		Message message =new Message();
		User user =userService.get(order.getUid());
		message.setUid(order.getUid());
		message.setOrderId(out_trade_no);//订单号
		message.setTitle("订单处理");
		message.setContent("手机号为："+user.getUserPhone()+" 的用户于  "+order.getPayTime()+" 时支付下单，请及时处理!!");
		message.setState(1);//状态：1未读2已读3跟进
		message.setAddtime(new Date());
		messageService.save(message);
		System.out.println("消息记录结束---------");
	}
}
