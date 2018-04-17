package com.menglin.invest.service;


import com.menglin.invest.entity.Questions;
import com.menglin.invest.util.PageBean;


public interface IQuestionsService {
	
    public Questions get(int questionId);
	public void save(Questions questions);
	public void delete(int questionId);
	public void update(Questions questions);
	PageBean<Questions> findByPage(Integer currentPage,Integer pageSize,Questions questions,String startTime,String endTime);
	
}
