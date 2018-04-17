package com.menglin.invest.service;

import com.menglin.invest.entity.Information;
import com.menglin.invest.util.PageBean;



public interface IinformationService {
		
    public Information get(int newsId);
	public void save(Information information);
	public void delete(int newsId);
	public void update(Information information);
	PageBean<Information> findByPage(Integer currentPage,Integer pageSize,Information information,String startTime,String endTime);
	
}
