package com.menglin.triproapp.vo;


import org.apache.poi.ss.formula.functions.T;

import com.menglin.triproapp.util.Result;

/** 
 * @author CGS 
 * @date 2018年5月22日 下午4:09:32 
 */
public class ResultMessageObject<T> {

	private Result result;
	
	private Object  object;
	
	private  int unreadCount ;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}

	
	
	
	
}
