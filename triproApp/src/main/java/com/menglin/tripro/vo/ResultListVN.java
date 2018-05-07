package com.menglin.tripro.vo;

import java.util.List;

import com.menglin.tripro.util.Result;

/** 
 * @author CGS 
 * @date 2018年1月31日 下午4:05:53 
 */
public class ResultListVN<T>{
	private Result result;
	private List<T> ResultList;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public List<T> getResultList() {
		return ResultList;
	}
	public void setResultList(List<T> resultList) {
		ResultList = resultList;
	}
	
}
