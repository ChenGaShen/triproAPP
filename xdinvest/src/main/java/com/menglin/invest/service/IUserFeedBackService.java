package com.menglin.invest.service;

import com.menglin.invest.entity.UserFeedback;
import com.menglin.invest.util.PageBean;



public interface IUserFeedBackService {
    
    public UserFeedback get(int userFedId);
	public void save(UserFeedback userFeedback);
	public void delete(int userFedId);
	public void update(UserFeedback userFeedback);
	PageBean<UserFeedback> findByPage(Integer currentPage,Integer pageSize,UserFeedback userFeedback,String startTime,String endTime);
	
}
