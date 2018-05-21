package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.vo.PayWxVO;


/** 
 * @author CGS 
 * @date 2018年2月8日 下午5:16:10 
 */
public interface IPayWxService {

	public PayWx get(int id);
	public void save(PayWx payWx);
	public void delete(int id);
	public void update(PayWx payWx);
	PageBean<PayWx> findByPage(Integer currentPage, Integer pageSize, PayWx model,String startTime,String endTime);
	List<PayWxVO> outExport(PayWx model,String startTime,String endTime);
}
