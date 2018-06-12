package com.menglin.triproapp.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.Order;
import com.menglin.triproapp.entity.OrderItem;
import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.service.IOrderItemService;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.service.IPayWxService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.pay.weixin.CommonUtil;
import com.menglin.triproapp.util.pay.weixin.PayOrderThread;
import com.menglin.triproapp.util.pay.weixin.RequestHandler;
import com.menglin.triproapp.util.pay.weixin.Sha1Util;
import com.menglin.triproapp.util.pay.weixin.WeixinPayUtil;
import com.menglin.triproapp.vo.WexinPayInfo;


/**
 * 微信支付
 */

@Controller
@RequestMapping("/web/wxpay")
@SuppressWarnings("rawtypes")
public class WeixinPayController {
	
	
	@Resource  
    private IUserService userService;
	
	@Resource  
    private ICommodityService commodityService;
	
	@Resource  
    private ICommoditySeckillService commoditySeckillService;
	
	
	@Resource  
    private IOrderService orderService;
	
	@Resource
	private  IOrderItemService orderItemService;
	
	@Resource
	private  IPayWxService payWxService;
	
	@Resource  
    private IMessageService messageService;
	
	@Resource
	private IActiveRedService activeRedService;
	
	
	@RequestMapping(value="/toWeiXinAppPay.json",method = {RequestMethod.POST})
	public @ResponseBody WexinPayInfo toWeiXinAppPay(HttpServletRequest request, HttpServletResponse response){
		WexinPayInfo wxInfo=new WexinPayInfo();
		try {
			String orderId = request.getParameter("orderId");
			//得到openid
	        String openId = request.getParameter("openId");
			System.out.println("openId:"+openId);
			
			//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			//随机数 
			//String nonce_str = "1add1a30ac87aa2db72f57a2375d8fec";
			String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
			//商品标题
			Order  order=orderService.get(orderId);
			String body = "多趣宝订单";
			System.out.println("订单金额:"+WeixinPayUtil.getMoney(order.getOrderPrice().toString()));
			String totalFeeStr = order.getOrderPrice().toString();//订单支付金额
			String total_fee = WeixinPayUtil.getMoney(totalFeeStr);//元转为分
			System.out.println("in toPay,total_fee:" + total_fee);
//			测试使用
			total_fee="1"; 
			//商户订单号
			String out_trade_no = orderId;
			//订单生成的机器 IP
			String spbill_create_ip = request.getRemoteAddr();
					
			//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url = SystemParam.DOMAIN_NAME + "/web/wxpay/notifyUrl.json";
			
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", SystemParam.WX_APPID);
			packageParams.put("mch_id", SystemParam.WX_Partner);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", total_fee);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", SystemParam.WX_TradeType);  
			packageParams.put("openid", openId);  

			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(SystemParam.WX_APPID, SystemParam.WX_AppSecret, SystemParam.WX_Partnerkey);
			
			String sign = reqHandler.createSign(packageParams);
			System.out.println("sign:"+sign);
			String xml="<xml>"+
					"<appid>"+SystemParam.WX_APPID+"</appid>"+
					"<mch_id>"+SystemParam.WX_Partner+"</mch_id>"+
					"<nonce_str>"+nonce_str+"</nonce_str>"+
					"<sign>"+sign+"</sign>"+
					"<body><![CDATA["+body+"]]></body>"+
					"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
					"<total_fee>"+total_fee+"</total_fee>"+
					"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
					"<notify_url>"+notify_url+"</notify_url>"+
					"<trade_type>"+SystemParam.WX_TradeType+"</trade_type>"+
					"<openid>"+openId+"</openid>"+
					"</xml>";
			System.out.println("xml："+xml);
			
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id="";
			try {
				prepay_id = WeixinPayUtil.getPayNo(createOrderURL, xml);
				System.out.println("prepay_id:" + prepay_id);
				if(prepay_id.equals("")){
					request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					System.out.println("统一支付接口获取预支付订单出错");
					wxInfo.setResult(Result.fal("请求失败,统一支付接口获取预支付订单出错"));	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				wxInfo.setResult(Result.fal("请求失败!!"));
			}
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			String packages = "prepay_id="+prepay_id;
			finalpackage.put("appId", SystemParam.WX_APPID);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonce_str);
			finalpackage.put("package", packages);
			finalpackage.put("signType", SystemParam.WX_SignType);
			String finalsign = reqHandler.createSign(finalpackage);
			System.out.println("/jsapi?appid="+SystemParam.WX_APPID+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+packages+"&sign="+finalsign);
			// 返回微信参数
			wxInfo.setAppid(SystemParam.WX_APPID);
			wxInfo.setTimeStamp(timestamp);
			wxInfo.setNonceStr(nonce_str);
			wxInfo.setPackageValue(packages);
			wxInfo.setSign(finalsign);
			wxInfo.setOrderId(orderId);
			wxInfo.setPayPrice(total_fee);
			wxInfo.setResult(Result.suc("请求成功!!"));		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			wxInfo.setResult(Result.fal("请求失败!!"));	
		}
		return  wxInfo;
		
	}
	
	/**
	 * 微信异步回调，不会跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/notifyUrl.json")
	public void  weixinReceive(HttpServletRequest request,HttpServletResponse response, Model model){
		    String out_trade_no=null;
		    String return_code =null;
		    String total_fee=null;
		    String bank_type =null;
		    String openid=null;
		    String transaction_id=null;
		    String time_end=null;
//		    String result="";////返回给微信的处理结果 
		    try {
		        InputStream inStream = request.getInputStream();
		        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		        byte[] buffer = new byte[1024];
		        int len = 0;
		        while ((len = inStream.read(buffer)) != -1) {
		            outSteam.write(buffer, 0, len);
		        }
		        outSteam.close();
		        inStream.close();
		        String resultStr  = new String(outSteam.toByteArray(),"utf-8");
		        System.out.println("支付成功的回调："+resultStr);
		        Map<String, Object> resultMap = parseXmlToList(resultStr);
//		        String result_code = (String) resultMap.get("result_code");
		        String sign = (String) resultMap.get("sign");
		        out_trade_no = (String) resultMap.get("out_trade_no");
		        return_code = (String) resultMap.get("return_code");
		        total_fee = (String) resultMap.get("total_fee");
		        bank_type =(String) resultMap.get("bank_type");
		        openid =(String) resultMap.get("openid");
		        transaction_id =(String) resultMap.get("transaction_id");
		        time_end =(String) resultMap.get("time_end");
		       
		        
		        request.setAttribute("out_trade_no", out_trade_no);
		        //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
		        response.getWriter().write(RequestHandler.setXML("SUCCESS", "OK"));
		       
		    
		    }  catch (Exception e) {
		       System.out.println("微信回调接口出现错误~");
		        try {
		            response.getWriter().write(RequestHandler.setXML("FAIL", "error"));
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		    } 
		    if(return_code.equals("SUCCESS")){
//		    	 result=RequestHandler.setXML("SUCCESS", "OK");
//		    	  System.out.println(RequestHandler.setXML("SUCCESS", "OK"));
		        //支付成功的业务逻辑
		    	//处理支付成功以后的逻辑,如果有订单的处理订单
        		Order  order=orderService.get(out_trade_no);
        		if (order.getState()!=1) { //状态：0待付款1已付款2取消订单3已失效
        			
        			//开一个线程，更新订单状态  
        			new PayOrderThread(order, new Date(), userService,orderService,payWxService,messageService,activeRedService,out_trade_no, total_fee, transaction_id, bank_type, time_end).start();
				}
        		
        
		    }else{
//		    	 result=RequestHandler.setXML("FAIL", "resultStr is null");
//		    	 System.out.println(RequestHandler.setXML("FAIL", "resultStr is null"));
		        //支付失败的业务逻辑
		    	Order  order=orderService.get(out_trade_no);
		    	List<OrderItem> items =orderItemService.findListByOrderId(out_trade_no);
		    	if (CheckData.isNotEmpty(items)) {
		    		if (order.getSeckillState()==1) {//订单秒杀状态0秒杀订单1普通订单
						for (int i = 0; i < items.size(); i++) {//普通商品修改
							Commodity commodity=commodityService.get(items.get(i).getCommodityId());
					    	commodity.setAllowance(commodity.getAllowance()+items.get(i).getAmount());//付款失败，修改商品的余量。
					    	commodity.setRealSale(commodity.getRealSale()-items.get(i).getAmount());//付款失败，修改商品的真实销量
					    	commodity.setVirtualSales(commodity.getVirtualSales()-items.get(i).getAmount());//付款失败，修改商品的虚拟销量
					    	commodityService.update(commodity);
						}
					}else{// 秒杀商品修改
						for (int i = 0; i < items.size(); i++) {
							CommoditySeckill seckill =commoditySeckillService.get(items.get(i).getSeckillId());
							seckill.setSeckillAllowance(seckill.getSeckillAllowance()+items.get(i).getAmount());//取消订单，修改秒杀商品的余量。
							seckill.setSeckillRealSale(seckill.getSeckillRealSale()-items.get(i).getAmount());//取消订单，修改秒杀商品的真实销量
							seckill.setSeckillVirtualSales(seckill.getSeckillVirtualSales()-items.get(i).getAmount());//取消订单，修改秒杀商品的虚拟销量
							commoditySeckillService.update(seckill);
						}
					}	
				}
 	
		    }
//		    return result;
	}
	/**
	 * description: 解析微信通知xml
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unchecked" })
	private static Map parseXmlToList(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	
}
