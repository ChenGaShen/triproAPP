package com.menglin.tripro.util.pay.weixin;

import java.util.List;
import java.util.Map;

import com.menglin.tripro.util.SystemParam;
import com.menglin.tripro.util.pay.http.HttpGetRequest;
import com.menglin.tripro.vo.TicketJson;
import com.menglin.tripro.vo.TokenJson;
import com.menglin.tripro.vo.WxParams;

import net.sf.json.JSONObject;





public class WeixinShareUtil {

	
	//此处的appid与wx.config 参数appId一致   微信公众账号提供给开发者的信息，以下同理  
    public static String APPID = SystemParam.WX_APPID;  
      
    //同上  
    public static String APPSECRET = SystemParam.WX_AppSecret;  
      
    private static TokenJson getAccess_token(){  
  
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;  
        try {  
            String result = HttpGetRequest.doGet(url);  
            System.out.println("微信服务器获取token:"+result);  
            JSONObject rqJsonObject = JSONObject.fromObject(result);  
            TokenJson tokenJson = (TokenJson) JSONObject.toBean(rqJsonObject,TokenJson.class);  
            return tokenJson;  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
      
    private static TicketJson getTicket(String AccessToken){  
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+AccessToken+"&type=jsapi";  
        try {  
            String result = HttpGetRequest.doGet(url);  
            System.out.println("微信服务器获取Ticket:"+result);
            JSONObject rqJsonObject = JSONObject.fromObject(result);  
            TicketJson ticket = (TicketJson) JSONObject.toBean(rqJsonObject,TicketJson.class);  
            return ticket;  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    /** 
     * 获取js sdk 认证信息 
    * @author  
    * @date 创建时间 2016年7月28日 上午11:25:01  
    * @param url 
    * @return 
     */  
    public  static Map<String, String> getSign(String url){  
          
        //处理token失效的问题  
        try {
        	
            long tokenTimeLong = Long.parseLong(WxParams.tokenTime);  
            long tokenExpiresLong = Long.parseLong(WxParams.tokenExpires); 
              
            //时间差  
            long differ = (System.currentTimeMillis() - tokenTimeLong) /1000;  
            if (WxParams.token == null ||  differ > (tokenExpiresLong - 1800)) {  
                System.out.println("token为null，或者超时，重新获取");  
                TokenJson tokenJson = getAccess_token();  
                if (tokenJson != null) {  
                    WxParams.token = tokenJson.getAccess_token();  
                    WxParams.tokenTime = System.currentTimeMillis()+"";  
                    WxParams.tokenExpires = tokenJson.getExpires_in()+"";  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
            TokenJson tokenJson = getAccess_token();  
            if (tokenJson != null) {  
                WxParams.token = tokenJson.getAccess_token();  
                WxParams.tokenTime = System.currentTimeMillis()+"";  
                WxParams.tokenExpires = tokenJson.getExpires_in()+"";  
            }  
        }  
  
        //处理ticket失效的问题  
        try {  
            long ticketTimeLong = Long.parseLong(WxParams.ticketTime);  
            long ticketExpiresLong = Long.parseLong(WxParams.ticketExpires); 
              
            //时间差  
            long differ = (System.currentTimeMillis() - ticketTimeLong) /1000;  
            if (WxParams.ticket == null ||  differ > (ticketExpiresLong - 1800)) {  
                System.out.println("ticket为null，或者超时，重新获取");
                TicketJson ticketJson = getTicket(WxParams.token);  
                if (ticketJson != null) {  
                    WxParams.ticket = ticketJson.getTicket();  
                    WxParams.ticketTime = System.currentTimeMillis()+"";  
                    WxParams.ticketExpires = ticketJson.getExpires_in()+""; 
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
            TicketJson ticketJson = getTicket(WxParams.token);  
            if (ticketJson != null) {  
                WxParams.ticket = ticketJson.getTicket();  
                WxParams.ticketTime = System.currentTimeMillis()+"";  
                WxParams.ticketExpires = ticketJson.getExpires_in()+"";  
            }  
        }  
    	
    /*	// 第一次进入，查询数据库是否有存储的token值
    	List <AccessToken>  list =accessTokenService.findAccessToken();
    	//如若没有存储
    	if(list==null){
    		 TokenJson tokenJson = getAccess_token();
    		 AccessToken act=new AccessToken();
             if (tokenJson != null) { 
            	 act.setAccessToken(tokenJson.getAccess_token());  
            	 act.setCreateTime(System.currentTimeMillis()+"");  
            	 act.setExpiresIn(tokenJson.getExpires_in()+"");
            	 accessTokenService.add(act);
             }
    	}
    	else{
    		 AccessToken act1=list.get(0);
    		 //判断token 是否失效
             long tokenTimeLong = Long.parseLong(act1.getCreateTime());  
             long tokenExpiresLong = Long.parseLong(act1.getExpiresIn()); 
               
             //时间差  
             long differ = (System.currentTimeMillis() - tokenTimeLong) /1000;
    		 if(differ > (tokenExpiresLong - 1800)){
    			  System.out.println("token超时，重新获取");
    			 
    		 }
    		
    	}*/
        Map<String, String> ret = Sign.sign(WxParams.ticket, url);  
        System.out.println("计算出的签名-----------------------");  
        for (Map.Entry entry : ret.entrySet()) {  
            System.out.println(entry.getKey() + ", " + entry.getValue());  
        }  
        System.out.println("-----------------------");  
        return ret;
    }  
	    
}
