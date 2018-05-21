package com.menglin.triproapp.util;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class UtilMethod {
	public static sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

	public static String encode(byte[] bstr) {
		return encoder.encode(bstr);
	}
	

	
	public static boolean endpoints(String cc,String phone){
		try {
			String endpoint = "http://182.254.215.86:8282/axis/services/hysms";
			
			Service service = new Service();
			Call call = null;
			call = (Call) service.createCall();
			call.setOperationName(new QName(endpoint, "sendMessages2"));

			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			String t = "";
			String tt="【TRIPRO 多趣宝】敬爱的多趣宝用户，本次获取的验证码是"+cc+"，请勿将验证码泄露他人。";
			while(true) {
			long start = System.currentTimeMillis();
			t = (String) call.invoke(new Object[] {"1065812646", "hy8829ws",
					"1,"+phone+",1", tt, "" });
			if(t.startsWith("SUCCESS")){ 
			System.out.println("cost:"
					+ (double) (System.currentTimeMillis() - start) / 1000
					+ " seconds," + " returned:" + t);
			return true;
			}
			else{
				System.out.println("cost:"
						+ (double) (System.currentTimeMillis() - start) / 1000
						+ " seconds," + " returned:" + t);
				return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
