package com.menglin.tripro.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {
	public static boolean isNullOrZero(Integer args)
	  {
	    if (args != null) {
	      return args.intValue() == 0;
	    }
	    return true;
	  }

	  public static boolean isNullOrZero(Short args)
	  {
	    if (args != null) {
	      return args.shortValue() == 0;
	    }
	    return true;
	  }

	  public static boolean isEmpty(String args)
	  {
	    return (args == null) || ("".equals(args)) || (args.length() == 0);
	  }

	  public static boolean isEmpty(String[] arrs)
	  {
	    if (arrs == null) {
	      return true;
	    }
	    switch (arrs.length) {
	    case 0:
	      return true;
	    case 1:
	      return isEmpty(arrs[0]);
	    }
	    for (String i : arrs) {
	      if (isEmpty(i)) {
	        return true;
	      }
	    }

	    return false;
	  }

	  public static boolean isNotEmpty(int count, String[] arrs)
	  {
	    if (arrs == null) {
	      return false;
	    }
	    int i = 0;
	    for (String str : arrs) {
	      if (!isEmpty(str)) {
	        i++;
	      }
	    }
	    return i >= count;
	  }

	  public static boolean isDigit(String args)
	  {
	    if (!isEmpty(args)) {
	      return args.matches("^\\d+$");
	    }
	    return false;
	  }

	  public static boolean isNotEmpty(Object args)
	  {
	    if (args == null) {
	      return false;
	    }
	    if ((args instanceof String)) {
	      return args.toString().trim().length() != 0;
	    }
	    if ((args instanceof Integer)) {
	      return ((Integer)args).intValue() != 0;
	    }
	    if ((args instanceof Long)) {
	      return ((Long)args).longValue() != 0L;
	    }
	    if ((args instanceof Float)) {
	      return ((Float)args).floatValue() != 0.0D;
	    }
	    if ((args instanceof Double)) {
	      return ((Double)args).doubleValue() != 0.0D;
	    }
	    if ((args instanceof Object[])) {
	      return ((Object[])args).length != 0;
	    }
	    if ((args instanceof Collection)) {
	      return ((Collection)args).size() != 0;
	    }
	    if ((args instanceof Map)) {
	      return ((Map)args).size() != 0;
	    }
	    return false;
	  }
	  public static boolean isNullOrEmpty(Object obj) {  
	        if (obj == null)  
	            return true;  
	  
	        if (obj instanceof CharSequence)  
	            return ((CharSequence) obj).length() == 0;  
	  
	        if (obj instanceof Collection)  
	            return ((Collection) obj).isEmpty();  
	  
	        if (obj instanceof Map)  
	            return ((Map) obj).isEmpty();  
	  
	        if (obj instanceof Object[]) {  
	            Object[] object = (Object[]) obj;  
	            if (object.length == 0) {  
	                return true;  
	            }  
	            boolean empty = true;  
	            for (int i = 0; i < object.length; i++) {  
	                if (!isNullOrEmpty(object[i])) {  
	                    empty = false;  
	                    break;  
	                }  
	            }  
	            return empty;  
	        }  
	        return false;  
	    }  
	  
	  public static boolean isNotNullOrEmpty(Object obj) {  
		  return !isNullOrEmpty(obj);
	  }

	  public static boolean isNotEmptyString(String args)
	  {
	    if (!isEmpty(args)) {
	      return !"null".equals(args);
	    }
	    return false;
	  }

	  public static boolean isDate(String args)
	  {
	    if (!isEmpty(args)) {
	      String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	      Pattern pat = Pattern.compile(rexp);
	      Matcher mat = pat.matcher(args);
	      return mat.matches();
	    }
	    return false;
	  }

	  public static boolean isIdCard(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^\\d{15}(\\d{2}[\\d|X|x])?$");
	    }
	    return false;
	  }

	  public static boolean isTelephone(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^(\\d{4}[-]\\d{7})$|^(\\d{3}[-]\\d{8})$|^(\\d{7,8})$");
	    }

	    return false;
	  }

	  public static boolean isMobilephone(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^[1][3|4|5|6|7|8|9]\\d{9}$");
	    }
	    return false;
	  }

	  public static boolean isPostcode(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^\\d{6}$");
	    }
	    return false;
	  }

	  public static boolean isMoney(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^\\d+(([.]?\\d{1,2}$)|(\\d*$))");
	    }
	    return false;
	  }

	  public static boolean isAge(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^[1-9][0-9]?$");
	    }
	    return false;
	  }

	  public static boolean isChinese(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "[一-龥]+");
	    }
	    return false;
	  }

	  public static boolean isUserName(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^[_|a-zA-Z]+\\w*$");
	    }
	    return false;
	  }

	  public static boolean isMail(String source) {
	    if (!isEmpty(source)) {
	      return match(source, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*[.]\\w+([-.]\\w+)*$");
	    }

	    return false;
	  }

	  public static boolean isUrl(String source)
	  {
	    if (!isEmpty(source)) {
	      return match(source, "^http://[A-Za-z0-9]+[.][A-Za-z0-9]+\\[/=\\?%-&_~`@[\\]':+!]*([^<>\"])*$");
	    }

	    return false;
	  }

	  public static boolean match(String source, String regex) {
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(source);
	    return matcher.matches();
	  }

	  public static boolean matchAsterisk(String regex, String source)
	  {
	    String execPattern = "^";
	    for (int i = 0; i < regex.length(); i++) {
	      char ch = regex.charAt(i);
	      if (ch == '*')
	        execPattern = execPattern + "\\S" + ch;
	      else {
	        execPattern = execPattern + ch;
	      }
	    }
	    execPattern = execPattern + "$";
	    return Pattern.matches(execPattern, source);
	  }

	  public static void main(String[] args)
	  {
	    System.out.println(isAge("1200"));
	  }
}
