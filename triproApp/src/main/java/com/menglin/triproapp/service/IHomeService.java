package com.menglin.triproapp.service;

import java.util.List;
import com.menglin.triproapp.entity.Home;
import com.menglin.triproapp.util.PageBean;


/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:44:29 
 */
public interface IHomeService {
	
		    void deleteByPrimaryKey(Integer id);
	
		    void save(Home record);
	
		    Home get(Integer id);
	
		    void update(Home record);
		    
		    List<Home> selectHomeList();
		 
		    PageBean<Home> findByPage(Integer currentPage,Integer pageSize,Home model);

		
	
		 
}
