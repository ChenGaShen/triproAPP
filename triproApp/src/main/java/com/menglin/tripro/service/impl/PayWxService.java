package com.menglin.tripro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.tripro.dao.OrderDao;
import com.menglin.tripro.dao.PayWxDao;
import com.menglin.tripro.entity.PayWx;
import com.menglin.tripro.service.IPayWxService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.DateUntil;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.vo.PayWxVO;
import com.menglin.tripro.vo.UserVO;

/** 
 * @author CGS 
 * @date 2018年2月8日 下午5:17:26 
 */
@Service("payWxService")
public class PayWxService implements IPayWxService {

	
	@Resource
	private  PayWxDao payWxDao;
	
	@Override
	public PayWx get(int id) {
		
		return payWxDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(PayWx payWx) {
		payWxDao.insertSelective(payWx);

	}

	@Override
	public void delete(int id) {
		payWxDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(PayWx payWx) {
		payWxDao.updateByPrimaryKeySelective(payWx);

	}
	
	@Override
	public PageBean<PayWx> findByPage(Integer currentPage, Integer pageSize,PayWx model,String startTime,String endTime) {
		
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("userPhone", model.getUserPhone());
        map1.put("outTradeNo", model.getOutTradeNo());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = payWxDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
        map.put("userPhone",  model.getUserPhone());
        map.put("outTradeNo", model.getOutTradeNo());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<PayWx> lists = payWxDao.findByPage(map);
        
        PageBean<PayWx> pageBean = new PageBean<PayWx>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	@Override
	public List<PayWxVO> outExport(PayWx model, String startTime, String endTime) {
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        map.put("userPhone",  model.getUserPhone());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("outTradeNo", model.getOutTradeNo());
        List<PayWx> lists = payWxDao.findByPage(map);
        List<PayWxVO> wxVOs =new ArrayList<PayWxVO>();
        		for (int i = 0; i < lists.size(); i++) {
        			PayWxVO vo =new PayWxVO();
        			vo.setId(lists.get(i).getId());
        			vo.setUid(lists.get(i).getUid());
        			vo.setUserPhone(lists.get(i).getUserPhone());
        			vo.setOutTradeNo(lists.get(i).getOutTradeNo());
        			vo.setTransactionId(lists.get(i).getTransactionId());
        			vo.setTotalFee(lists.get(i).getTotalFee());
        			vo.setCashFee(lists.get(i).getCashFee());
        			vo.setBankType(lists.get(i).getBankType());
        			vo.setTimeEnd(lists.get(i).getTimeEnd());
        			vo.setAddTime(lists.get(i).getAddTime());
        			wxVOs.add(vo);
				}
        return wxVOs;
	}


}
