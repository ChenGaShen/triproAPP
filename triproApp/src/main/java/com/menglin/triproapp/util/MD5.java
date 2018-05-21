package com.menglin.triproapp.util;

import java.security.MessageDigest;

/** 
 * @author CGS 
 * @date 2018年2月28日 下午4:46:03 
 */
public class MD5 {
	public static String md5As16(String s)
	  {
	    byte[] source = s.getBytes();
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    try
	    {
	      MessageDigest md = MessageDigest.getInstance("MD5");

	      md.update(source);
	      byte[] tmp = md.digest();

	      char[] str = new char[32];

	      int k = 0;
	      for (int i = 0; i < 16; i++)
	      {
	        byte byte0 = tmp[i];
	        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

	        str[(k++)] = hexDigits[(byte0 & 0xF)];
	      }
	      s = new String(str);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return s;
	  }

	  public static final String md5(String s) {
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    try
	    {
	      byte[] strTemp = s.getBytes("UTF-8");
	      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	      mdTemp.update(strTemp);
	      byte[] md = mdTemp.digest();
	      int j = md.length;
	      char[] str = new char[j * 2];
	      int k = 0;
	      for (int i = 0; i < j; i++) {
	        byte byte0 = md[i];
	        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
	        str[(k++)] = hexDigits[(byte0 & 0xF)];
	      }
	      return new String(str); } catch (Exception e) {
	    }
	    return null;
	  }

	  public static void main(String[] args)
	  {
	    try {
	      String str = "accountId18956792850accountTypeSerial NumberareaId571srcSysID01inputTime20111117170001clientIP192.168.1.167ndf6l4onxqr4lbvc2dexzonf5ntvkrfp";
	      System.out.println(str);
	      System.out.println(md5(str).length());
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }
}
