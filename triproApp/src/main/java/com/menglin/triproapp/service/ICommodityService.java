package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;

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
		    
		    List<CommodityListInfo> selectCommodityList(Integer condition,Integer classify,String commodityName);
		    
		    PageBean<CommodityDetailVO> findByPage(Integer currentPage,Integer pageSize,Commodity model);

			CommodityDetailVO selectCommodityDetail(Integer commodityId);
	
		 
}
