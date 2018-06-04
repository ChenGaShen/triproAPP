package com.menglin.triproapp.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.menglin.triproapp.common.Recruitment;
import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Adress;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.OrderItem;
import com.menglin.triproapp.entity.Shopping;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.IAdressService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.service.IOrderItemService;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.service.IPayWxService;
import com.menglin.triproapp.service.IShoppingService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.OrderUtils;
import com.menglin.triproapp.util.PhoneUtils;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.pay.weixin.PayOrderThread;
import com.menglin.triproapp.vo.LogisticsInfo;
import com.menglin.triproapp.vo.LogisticsVO;
import com.menglin.triproapp.vo.OrderItemForm;
import com.menglin.triproapp.vo.OrederDetailVO;
import com.menglin.triproapp.vo.ResultOrderList;
import com.menglin.triproapp.vo.ResultOrderVN;
import com.menglin.triproapp.vo.ResultVN;
import com.menglin.triproapp.vo.ShoppingListVO;
import com.menglin.triproapp.vo.LogisticsInfo.ResultBean.ListBean;
import com.menglin.triproapp.vo.ShoppingListVO.ResultListBean;

import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

/** 
 * @author CGS 
 * @date 2018年2月6日 上午9:58:52 
 */
@Controller  
@RequestMapping("/web/wxorder")
public class OrderWebController {
	

	@Resource  
    private ICommodityService commodityService;
	
	@Resource  
    private IOrderService orderService; 
	
	@Resource
	private  IOrderItemService orderItemService;
	
	@Resource  
    private IShoppingService shoppingService;
	

	@Resource  
    private IUserService userService;
	
	@Resource  
    private IAdressService adressService;
	
	@Resource  
	private IMessageService messageService;
	
	@Resource
	private  IPayWxService payWxService;
	
	@Resource
	private IActiveRedService activeRedService;
	
	/**
	 * 购物车下单
	 * @author CGS
	 * @time 2018年2月6日下午3:27:31
	 * @param model
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doCartOrder.json",method = {RequestMethod.POST})
	public @ResponseBody ResultOrderVN doCartOrder(Order model, Integer activeid ,Integer addressId,String json,HttpServletRequest request,HttpServletResponse response){
		ResultOrderVN vn=new ResultOrderVN();
		
		User user=userService.get(model.getUid());
	if (user.getState()!=2) {
			// //开始下单
			// //***************************************
			Gson gson =new Gson();
			JSONObject object=JSONObject.fromObject(json);
			ShoppingListVO shoppingListVO =new ShoppingListVO();
			shoppingListVO=gson.fromJson(object.toString(), ShoppingListVO.class);
			if (null!=shoppingListVO) {
				List<ResultListBean> ls =new ArrayList<ResultListBean>();
				ls=shoppingListVO.getResultList();
				String autOrderId =OrderUtils.genOrderNo(); //工具类生成订单ID
				Double OrderPrice=0.00d;//初始化订单总额
				for (int i = 0; i < ls.size(); i++) {
					Commodity commodity =commodityService.get(ls.get(i).getCommodityId());
				if (null!=commodity) {
					
					if ((commodity.getAllowance() - ls.get(i).getNum()) < 0) {
						vn.setResult(Result.fal("您的购买的"+commodity.getCommodityName()+"商品库存不足!!"));
						return vn;
					}else{
						
						commodity.setAllowance(commodity.getAllowance()- ls.get(i).getNum());
						commodity.setVirtualSales(commodity.getVirtualSales()+ls.get(i).getNum());//虚拟销量改变
						commodity.setRealSale(commodity.getRealSale()+ls.get(i).getNum());//真是销量改变
						commodity.setUpdateTime(new Date());
						commodityService.update(commodity);
						
						
						
						// 子订单项生成
						OrderItem orderItem =new OrderItem();
						
						orderItem.setCommodityId(ls.get(i).getCommodityId());
						orderItem.setCommodityName(commodity.getCommodityName());
						orderItem.setAmount(ls.get(i).getNum());
						orderItem.setImg(commodity.getCommodityImg());
						orderItem.setSpecification(commodity.getSpecification());
						orderItem.setPrice(commodity.getDiscountPrice());
						OrderPrice+=orderItem.getPrice()*ls.get(i).getNum();
						orderItem.setOrderId(autOrderId);
						orderItemService.save(orderItem);	
						delShoppingCommodity(ls.get(i).getCommodityId(),model.getUid());//购物车去除下单之后的商品
						
					}
				  }	
				}
				//总订单生成
				Adress adress =adressService.get(addressId);
				//填入默认地址
				if (null!=adress) {
					model.setReceiveName(adress.getReceivedName());
					model.setReceivePhone(adress.getReceivedPhone());
					model.setReceiveAddress(adress.getReceivedProvince()+adress.getReceivedCity()+adress.getReceivedCanton()+adress.getReceivedDetail());//拼接收货地址
				}
				model.setId(autOrderId);
				model.setUid(model.getUid());
				ActiveRed  activeRed=activeRedService.get(activeid);
				if (CheckData.isNotNullOrEmpty(activeRed.getActiveid())) {
					model.setOrderPrice(OrderPrice-activeRed.getRedMoney());//使用红包 金额减少
					model.setMoney(activeRed.getRedMoney());//订单中的红包金额
				}else{
					model.setOrderPrice(OrderPrice);//订单总价
				}
	//			model.setMoney(OrderPrice);//实际支付金额
				model.setState(0);//状态：0待付款1已付款2取消订单3已失效
	//			model.setReceiveState(0);//订单状态0待发货1配送中2已签收
				if (CheckData.isNotNullOrEmpty(model.getRemark())) {
					model.setRemark(model.getRemark());//订单备注
				}
				model.setAddTime(new Date());
				
				// 订单IP信息
				String ip = request.getHeader("X-Forwarded-For");
				if ((ip == null) || (ip.length() == 0)|| ("unknown".equalsIgnoreCase(ip)))
					ip = request.getHeader("Proxy-Client-IP");
				if ((ip == null) || (ip.length() == 0)|| ("unknown".equalsIgnoreCase(ip)))
					ip = request.getHeader("WL-Proxy-Client-IP");
				if ((ip == null) || (ip.length() == 0)|| ("unknown".equalsIgnoreCase(ip)))
					ip = request.getHeader("HTTP_CLIENT_IP");
				if ((ip == null) || (ip.length() == 0)|| ("unknown".equalsIgnoreCase(ip)))
					ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if ((ip == null) || (ip.length() == 0)|| ("unknown".equalsIgnoreCase(ip)))
					ip = request.getRemoteAddr();
				if (("127.0.0.1".equals(ip))
						|| ("0:0:0:0:0:0:0:1".equals(ip)))
					try {
						ip = InetAddress.getLocalHost().getHostAddress();
					} catch (UnknownHostException localUnknownHostException) {
	
					}
				model.setIp(ip);
				String ipAddress = "";
				try {
					ipAddress = PhoneUtils.getIPBelonging(ip);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//订单iP 信息结束
				model.setIpAddress(ipAddress);
				//总订单生成
				orderService.save(model);			
				vn.setResult(Result.suc("购物车下单成功，跳转付款页面..."));
				vn.setOrderId(autOrderId);
//				new WeixinPayController().toWeiXinAppPay(request,response);
				return vn;
			}else{
				vn.setResult(Result.fal("空空如也,快去逛逛吧~"));
				return vn;
			}
			
		}else{
			vn.setResult(Result.fal("账户已被冻结!!"));
			return vn;
		}
	}
	
	
	/**
	 * 清空购物车
	 * @author CGS
	 * @time 2018年2月5日下午5:31:04
	 * @param uid
	 * @return
	 */
	public void  emptyCart(Integer uid){
	
		shoppingService.emptyCart(uid);
		
	}
	
	/**
	 * 购物车删除商品
	 * @author CGS
	 * @time 2018年2月5日下午3:56:35
	 * @param commodityId
	 * @param uid
	 * @return
	 */
	public @ResponseBody ResultVN delShoppingCommodity(Integer commodityId,Integer uid){
		ResultVN vn=new ResultVN();
		shoppingService.deleteCommodity(commodityId,uid);
		vn.setResult(Result.suc("购物车去除成功!!"));
		return vn;
	}
	
	/**
	 * 订单地址更新
	 * @author CGS
	 * @time 2018年3月13日下午3:45:47
	 * @param orderId
	 * @param receiveName
	 * @param receivePhone
	 * @param receiveAddress
	 * @return
	 */
	@RequestMapping(value="/updateOrderAddress.json",method = {RequestMethod.POST})
	public @ResponseBody OrederDetailVO updateOrderAddress(String orderId, String receiveName,String receivePhone,String receiveAddress){
		Order order =orderService.get(orderId);
		OrederDetailVO orederDetailVO=new OrederDetailVO();
		if (null!=order) {
			order.setReceiveName(receiveName);
			order.setReceivePhone(receivePhone);
			order.setReceiveAddress(receiveAddress);
			orderService.update(order);
			orederDetailVO=orderService.findByOrederId(orderId);
			orederDetailVO.setResult(Result.suc("收货地址更新成功!!"));
			return orederDetailVO;
		}else{
			orederDetailVO.setResult(Result.fal("订单不存在!!"));
			return orederDetailVO;
		}
		
	}
	
	
	/**
	 * 根据订单ID ,查询订单详情
	 * @author CGS
	 * @time 2018年2月7日上午10:45:36
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/toOrderDetail.json",method = {RequestMethod.POST})
	public @ResponseBody OrederDetailVO toOrderDetail(String orderId){
		OrederDetailVO orederDetailVO=new OrederDetailVO();
		orederDetailVO=orderService.findByOrederId(orderId);
		orederDetailVO.setResult(Result.suc("查询成功!!"));
		return orederDetailVO;
	}
	
	/**
	 * 根据用户ID 查询其所有订单
	 * @author CGS
	 * @time 2018年2月7日上午10:45:14
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/toOrderList.json",method = {RequestMethod.POST})
	public @ResponseBody ResultOrderList toOrderList(Integer uid ){
		ResultOrderList orderList=new ResultOrderList();
		orderList=orderService.selectOrderListByUid(uid);
		return orderList;
	}
	
	/**
	 * 根据订单支付状态和接收状态查询订单信息
	 * @author CGS
	 * @time 2018年2月7日上午10:58:50
	 * @param uid
	 * @param state
	 * @param receiveState
	 * @return
	 */
	@RequestMapping(value="/toOrderStatus.json",method = {RequestMethod.POST})
	public @ResponseBody ResultOrderList toOrderStatus(Integer uid,Integer state ,Integer receiveState ){
		 
		ResultOrderList	orderList=orderService.selectOrderListByUidAndStatus(uid, state, receiveState);
		
		return orderList;
	}
	
	/**
	 * 根据订单查询物流
	 * @author CGS
	 * @time 2018年2月7日下午4:14:12
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/doExpressCheck.json",method = {RequestMethod.POST})
	public @ResponseBody LogisticsVO doExpressCheck(String orderId){
		Order order =orderService.get(orderId);
		LogisticsVO logisticsVO=new LogisticsVO();
		if (null!=order) {
			if (CheckData.isNotEmptyString(order.getAir()) && CheckData.isNotEmptyString(order.getCompany()) && order.getState() ==1 ) {
				
				Map<String,Object> params =new HashMap<String,Object>();
				params.put("com", order.getCompany());//需要查询的快递公司编号 eg:yd，sf
	            params.put("no",order.getAir());//需要查询的订单号
	            JSONObject object=Recruitment.getRequest1(params);
	            if (object!=null) {
	            	LogisticsInfo logisticsInfo =new LogisticsInfo();
		            Gson gson =new Gson();
		            logisticsInfo=gson.fromJson(object.toString(), LogisticsInfo.class);
		            List<ListBean>  beans= logisticsInfo.getResult().getList();
		            Collections.reverse(beans); //list 数据逆序排列
		            logisticsInfo.getResult().setList(beans);
		          	logisticsVO.setResultInfo(logisticsInfo);
		            logisticsVO.setResult(Result.suc("物流查询成功"));
				}else{
					logisticsVO.setResult(Result.fal("物流进度查询有误，请核对物流信息"));
				}
	            
	    		return logisticsVO;
			}else{
				logisticsVO.setResult(Result.fal("物流信息未录入"));
				return logisticsVO;
			}
		} 
		logisticsVO.setResult(Result.fal("订单号有误~"));
		return logisticsVO;
	}
	
	/**
	 * 用户确认收货
	 * @author CGS
	 * @time 2018年3月12日下午3:24:16
	 * @param uid
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/doConfirmReceipt.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN doConfirmReceipt(Integer uid,String  orderId ){
		
		ResultVN vn = new ResultVN();
		Order order=  orderService.get(orderId);
	if (null!=order) {
		
		if (order.getState()==1 && order.getReceiveState() == 0) { //状态：0待付款1已付款2取消订单3已失效
			order.setReceiveState(2);//订单状态0待发货1配送中2已签收
			orderService.update(order);
			vn.setResult(Result.suc("确认收货成功!"));
			return vn;
		}else{
			vn.setResult(Result.fal("未支付或者未发货，不能进行此操作!!"));
			return vn;
		}
	}else{
		vn.setResult(Result.fal("订单号有误~"));
		return vn;
	}	
		
	}
	
	/**
	 * 用户取消订单
	 * @author CGS
	 * @time 2018年3月12日下午3:25:26
	 * @param uid
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/doCancellOrder.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN doCancellOrder(Integer uid,String  orderId ){
		
		ResultVN vn = new ResultVN();
		Order order=  orderService.get(orderId);
		if (null!=order) {
			
			
			if (order.getState()==0) { //状态：0待付款1已付款2取消订单3已失效
				order.setState(2);//状态：0待付款1已付款2取消订单3已失效
				orderService.update(order);
				
		    	List<OrderItem> items =orderItemService.findListByOrderId(orderId);
		    	if (CheckData.isNotEmpty(items)) {
					for (int i = 0; i < items.size(); i++) {
						Commodity commodity=commodityService.get(items.get(i).getCommodityId());
				    	commodity.setAllowance(commodity.getAllowance()+items.get(i).getAmount());//取消订单，修改商品的余量。
				    	commodityService.update(commodity);
					}
				}
				vn.setResult(Result.suc("订单取消成功!"));
				return vn;
			}else{
				vn.setResult(Result.fal("操作有误!!"));
				return vn;
			}
		}else{
			vn.setResult(Result.fal("订单号有误~"));
			return vn;
		}
	}	
	
}
