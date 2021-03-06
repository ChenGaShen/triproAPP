package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.CommodityDetails;
import com.menglin.triproapp.vo.CommodityDetailImgVO;


/** 
 * @author CGS 
 * @date 2018年5月22日 下午2:47:09 
 */
public interface ICommodityDetailImgService {
	
	 void deleteByPrimaryKey(Integer id);
		
	    void save(CommodityDetails record);

	    CommodityDetails get(Integer id);

	    void update(CommodityDetails record);
	    
	    List<String> findByCommodityId(Integer commodityId);
	    
	    List<String> findByCommoditySeckillId(Integer seckillId);
	    
	    CommodityDetailImgVO findAdminByCommodityId(Integer commodityId);
	    
	    CommodityDetailImgVO findAdminByCommoditySeckillId(Integer seckillId);
	    
	    
	   /* void soldOutCommodity(Integer[] commodityIds);
	    
	    List<CommodityDetailVO> selectCommodityList(Integer uid);
	    
	    PageBean<CommodityDetailVO> findByPage(Integer currentPage,Integer pageSize,CommodityDetails model);*/

}
