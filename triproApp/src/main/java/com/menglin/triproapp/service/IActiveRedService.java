package com.menglin.triproapp.service;

import java.util.List;
import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年5月28日 下午3:54:04 
 */
public interface IActiveRedService {

	void delete(Integer id);

    void save(ActiveRed record);

    ActiveRed get(Integer id);
    
    ActiveRed selectByorderId(String orderId); 

    void update(ActiveRed record);
    
    List<ActiveRed> activeRedList(Integer uid);
    
	PageBean<ActiveRed> findByPage(Integer currentPage,Integer pageSize,ActiveRed model);
}
