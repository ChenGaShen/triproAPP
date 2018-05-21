package com.menglin.triproapp.web;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.pay.weixin.WeixinShareUtil;
import com.menglin.triproapp.vo.WeiXinShareInfo;

@Controller
@RequestMapping("/web/share")
public class WeixinShareController{
		
	   	/**
	   	 * 前端获取分享Config数据
	   	 * @param request
	   	 * @param url
	   	 * @return
	   	 */
	 	@RequestMapping("/toShare")
	    public @ResponseBody WeiXinShareInfo share(HttpServletRequest request,String url) {
	 		System.out.println(url);
	 		Map<String,String> sign = WeixinShareUtil.getSign(url);
	 		WeiXinShareInfo wxShareInfo=new WeiXinShareInfo();
	 		wxShareInfo.setAppId(SystemParam.WX_APPID);
	 		wxShareInfo.setTimestamp(sign.get("timestamp"));
	 		wxShareInfo.setNonceStr(sign.get("nonceStr"));
	 		System.out.println(sign.get("signature"));
	 		wxShareInfo.setSignature(sign.get("signature"));
	 		wxShareInfo.setJsapi_ticket(sign.get("jsapi_ticket"));
	 	
	        return wxShareInfo;
	    }
}
