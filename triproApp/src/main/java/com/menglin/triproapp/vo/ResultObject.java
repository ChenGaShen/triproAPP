package com.menglin.triproapp.vo;


import org.apache.poi.ss.formula.functions.T;

import com.menglin.triproapp.util.Result;

/** 
 * @author CGS 
 * @date 2018年5月22日 下午4:09:32 
 */
public class ResultObject<T> {

	private Result result;
	
	private Object  object;

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

	
	
	
}
