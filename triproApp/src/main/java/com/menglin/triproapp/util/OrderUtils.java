package com.menglin.triproapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单工具类
 * @author JeffXu
 * @since 2016-08-11
 */
public class OrderUtils {
	
	public static String genOrderNo(){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String orderNo = sdf.format(new Date());
		String random = getRandom620(3);
		return orderNo+=random;
	}
	
	/**
	 * 获取6-10 的随机位数数字
	 * @param length	想要生成的长度
	 * @return result
	 */
	public static String getRandom620(Integer length) {
		String result = "";
		Random rand = new Random();
		int n = 20;
		if (null != length && length > 0) {
			n = length;
		}
		int randInt = 0;
		for (int i = 0; i < n; i++) {
			randInt = rand.nextInt(10);
			result += randInt;
		}
		return result;
	}

}
