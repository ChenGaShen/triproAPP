package com.menglin.invest.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Format{
	/**以内
	 * @param args
	 */
	public static boolean formatTest(Date dace,int ss){

		Calendar ca=Calendar.getInstance();
        ca.setTime(dace);
		ca.add(Calendar.MINUTE,ss);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(ca.getTimeInMillis());
		Date date=new Date();
		String da=sdf.format(date);
		return compare_date(da, dateStr);
	}
	
	/**以外
	 * @param args
	 */
	public static boolean formatTest1(Date dace,int ss){
//		System.out.println("formatTest1执行啦！");
		Calendar ca=Calendar.getInstance();
        ca.setTime(dace);
		ca.add(Calendar.MINUTE,ss);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(ca.getTimeInMillis());
		Date date=new Date();
		String da=sdf.format(date);
//		System.out.println("当前时间"+da);
//		System.out.println("新增2h时间"+dateStr);
		return compare_date1(da, dateStr);
	}
	public static boolean formatTest2(Date dace, int hour) {
//		System.out.println("formatTest2执行啦！");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("支付时间"+sdf.format(dace));
		Calendar ca = Calendar.getInstance();
		ca.setTime(dace);
		ca.add(Calendar.HOUR_OF_DAY, hour);

		
		String dateStr = sdf.format(ca.getTimeInMillis());
		Date date = new Date();
		String da = sdf.format(date);
//		System.out.println("当前时间" + da);
//		System.out.println("新增2h时间" + dateStr);
		return compare_date1(da, dateStr);
	}
	public static boolean compare_date(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() <= dt2.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
	
	public static boolean compare_date1(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
//            System.out.println("1当前时间"+dt1.getTime());
//    		System.out.println("1新增2h时间"+dt2.getTime());
            if (dt1.getTime() > dt2.getTime()) {
//            	 System.out.println("-----------true---------------------");
                return true;
               
            } else if (dt1.getTime() <= dt2.getTime()) {
//            	System.out.println("-----------false---------------------");
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
	//判断当前时间是否属于某个时间段
	/**
	 * param DATE1 当前时间
	 * param DATE2 前时间点
	 * param DATE3 后时间点
	 */
	public static boolean compare_date2(String DATE1, String DATE2,String DATE3) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            Date dt3 = df.parse(DATE3);
            if (dt1.getTime() >= dt2.getTime() && dt1.getTime()<=dt3.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
	
	public static double formatMoney(double money) {
		DecimalFormat df = new DecimalFormat("#.00");
		String m = df.format(money);
		return Double.parseDouble(m);
	}
	public static String keepTwoMoney(Double money){
		DecimalFormat   myFormatter   =   new   DecimalFormat("######0.00");  
		return myFormatter.format(money); 
	}
	public static Date formatTime(String  time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =null;
		sdf.format(date);
		/*try {
			date = sdf.parse(time.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return date; 
	}
}
