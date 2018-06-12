package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;
import com.menglin.triproapp.vo.CommoditySeckillDetailVO;
import com.menglin.triproapp.vo.CommoditySeckillListInfo;

/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:44:29 
 */
public interface ICommoditySeckillService {
	
		    void deleteByPrimaryKey(Integer id);
	
		    void save(CommoditySeckill record);
	
		    CommoditySeckill get(Integer id);
	
		    void update(CommoditySeckill record);
		    
		    void soldOutCommoditySeckill(Integer[] CommoditySeckillIds);
		    
		    List<CommoditySeckillListInfo> selectCommoditySeckillList();
		    
		    List<CommoditySeckill> selectAllCommoditySeckill();
		    
		    PageBean<CommoditySeckillDetailVO> findByPage(Integer currentPage,Integer pageSize,CommoditySeckill model);

		    CommoditySeckillDetailVO selectCommoditySeckillDetail(Integer CommoditySeckillId);
	
		 
}
