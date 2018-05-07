package com.menglin.invest.service;

import java.util.List;

import com.menglin.invest.entity.UserFeedback;
import com.menglin.invest.util.PageBean;



public interface IUserFeedBackService {
    
    public UserFeedback get(int userFedId);
	public void save(UserFeedback userFeedback);
	public void delete(int userFedId);
	public void update(UserFeedback userFeedback);
	PageBean<UserFeedback> findByPage(Integer currentPage,Integer pageSize,UserFeedback userFeedback,String startTime,String endTime);
	public boolean deleteAll(int [] userFedIdS);
	List<UserFeedback> outExport(UserFeedback model,String startTime,String endTime);
	
}
