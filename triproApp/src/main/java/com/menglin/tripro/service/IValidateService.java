package com.menglin.tripro.service;

import com.menglin.tripro.entity.Validate;
import com.menglin.tripro.util.PageBean;

public interface IValidateService {
	
	public Validate isNoValidate(String phone, int validateType);
	
	public boolean verification(String phone, int validateType);
	
	public Validate doValidateCode(Validate validate);
	
	
	 int delete(Integer id);


	 int save(Validate record);

	 Validate get(Integer id);
	 
	 int update(Validate record);

	PageBean<Validate> findByPage(Integer currentPage, Integer pageSize, Validate model,String startTime,String endTime);

	
}
