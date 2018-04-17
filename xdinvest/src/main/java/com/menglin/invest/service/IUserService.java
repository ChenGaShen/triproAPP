package com.menglin.invest.service;


import com.menglin.invest.entity.User;
import com.menglin.invest.util.PageBean;

public interface IUserService {
	
	public User get(int userId);
	public void save(User user);
	public void delete(int userId);
	public void update(User user);
	public boolean unique(String userPhone);
	public User checkLogin(String userPhone, String userPass);
	PageBean<User> findByPage(Integer currentPage,Integer pageSize,User model,String startTime,String endTime);
	
	

}
