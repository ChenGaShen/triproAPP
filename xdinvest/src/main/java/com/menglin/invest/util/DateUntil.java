package com.menglin.invest.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public  class DateUntil{
	static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
	static Date date = null;
	static Calendar calendar= Calendar.getInstance();
		public static Date strToDate(String time){
			
			try {
				date = simpleDateFormat.parse(time);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}
	public  static String addOneDay(String time){
			try {
				date = simpleDateFormat.parse(time);
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			String tString=simpleDateFormat.format(calendar.getTime());
			return simpleDateFormat.format(calendar.getTime());
			
		}
	 public static String getLastDay(String datadate)throws Exception{
	        Date date = null;
	        String day_last = null;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        date = format.parse(datadate);
	        
	        //创建日历
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, 1);    //加一个月
	        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
//	        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
	        day_last = format.format(calendar.getTime());
	        return day_last;
	    }
	    
	    public static String getFirstDay(String datadate)throws Exception{
	        Date date = null;
	        String day_first = null;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        date = format.parse(datadate);
	        
	        //创建日历
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        day_first = format.format(calendar.getTime());
	        return day_first;
	    }
	    public static String getOneDay(String datadate,int day)throws Exception{
	        Date date = null;
	        String day_first = null;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        date = format.parse(datadate);
	        
	        //创建日历
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.DAY_OF_MONTH, day);
	        day_first = format.format(calendar.getTime());
	        return day_first;
	    }
}
