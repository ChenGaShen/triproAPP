package com.menglin.tripro.service;

import java.util.List;

import com.menglin.tripro.entity.Admin;
import com.menglin.tripro.entity.Commodity;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.vo.CommodityDetailVO;

/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:44:29 
 */
public interface ICommodityService {
	
		    void deleteByPrimaryKey(Integer id);
	
		    void save(Commodity record);
	
		    Commodity get(Integer id);
	
		    void update(Commodity record);
		    
		    void soldOutCommodity(Integer[] commodityIds);
		    
		    List<CommodityDetailVO> selectCommodityList(Integer uid);
		    
		    PageBean<CommodityDetailVO> findByPage(Integer currentPage,Integer pageSize,Commodity model);
	
		 
}
