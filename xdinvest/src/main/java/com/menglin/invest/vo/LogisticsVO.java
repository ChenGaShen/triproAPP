package com.menglin.invest.vo;

import com.menglin.invest.util.Result;

import net.sf.json.JSONObject;

/** 
 * @author CGS 
 * @date 2018年2月7日 下午4:04:39 
 */
public class LogisticsVO {

	private Result result;
	private LogisticsInfo resultInfo;


	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}


	public LogisticsInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(LogisticsInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	
}
