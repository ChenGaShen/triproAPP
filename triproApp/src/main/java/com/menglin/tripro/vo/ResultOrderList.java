package com.menglin.tripro.vo;

import java.util.List;
import com.menglin.tripro.util.Result;

/** 
 * @author CGS 
 * @date 2018年2月7日 上午9:50:10 
 */
public class ResultOrderList {

	private Result result; 
	
	private  List<OrederDetailVO> detailVOs;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<OrederDetailVO> getDetailVOs() {
		return detailVOs;
	}

	public void setDetailVOs(List<OrederDetailVO> detailVOs) {
		this.detailVOs = detailVOs;
	}
	
	
	
	
	
	
	
}
