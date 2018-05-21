
package com.menglin.triproapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemParam {

	/**
	 * 系统Url路径
	 */
	
	public static String DOMAIN_NAME = "";
	

	/**
	 * 私钥解密
	 */
	public static String PRIVATE_KEY = "";
	
	
	/** 
	 * 文件上传参数：
	 */
	public static String UPLOAD_MAX_WEB = "";
	public static String UPLOAD_MAX_ADMIN = "";
		
	
	
	/** 
	 * 微信支付参数：
	 */
	public static String WX_ORDER_URL = "";
	public static String WX_NOTIFY_URL = "";
	
	public static String WX_APPID_SHOP = "";	
	public static String WX_MCH_ID_SHOP = "";
	public static String WX_KEY_SHOP = "";
	
	public static String WX_APPID_USER = "";	
	public static String WX_MCH_ID_USER = "";
	public static String WX_KEY_USER = "";
	public static String  WX_APPID ="";
	public static String  WX_AppSecret ="";
	public static String  WX_Partner ="";
	public static String  WX_Partnerkey ="";
	public static String  WX_TradeType ="";
	public static String  WX_SignType ="";
	public static String  JH_Partnerkey ="";
	
    static  {    
        Properties prop =  new  Properties();  
        InputStream in = SystemParam.class.getClassLoader().getResourceAsStream("system.properties");
         try  {    
            prop.load(in);    
            DOMAIN_NAME = prop.getProperty("domainName"); 
            PRIVATE_KEY = prop.getProperty("privateKey"); 
            //文件上传
            UPLOAD_MAX_WEB = prop.getProperty("uploadMaxWeb");
            UPLOAD_MAX_ADMIN = prop.getProperty("uploadMaxAdmin");
          
            //微信
            WX_NOTIFY_URL = prop.getProperty("wxNotifyUrl");              
            WX_ORDER_URL = prop.getProperty("wxOrderUrl"); 
            WX_APPID_SHOP = prop.getProperty("wxAppIdShop"); 
            WX_MCH_ID_SHOP = prop.getProperty("wxMchIdShop"); 
            WX_KEY_SHOP = prop.getProperty("wxKeyShop");
            WX_APPID_USER = prop.getProperty("wxAppIdUser"); 
            WX_MCH_ID_USER = prop.getProperty("wxMchIdUser"); 
            WX_KEY_USER = prop.getProperty("wxKeyUser");
            WX_APPID=prop.getProperty("wxAppID");
            WX_AppSecret=prop.getProperty("wxAppSecret");
            WX_Partner=prop.getProperty("wxPartner");
            WX_Partnerkey=prop.getProperty("wxPartnerkey");
            WX_TradeType=prop.getProperty("wxTradeType");
            WX_SignType=prop.getProperty("weSignType");
            
            //聚合
            JH_Partnerkey=prop.getProperty("jhPrivateKey");
            
            in.close();
        }  catch  (IOException e) {    
            e.printStackTrace();    
        }    
    } 
    
}
