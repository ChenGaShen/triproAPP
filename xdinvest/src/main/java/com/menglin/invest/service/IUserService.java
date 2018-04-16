package com.menglin.invest.service;

import java.util.List;

import com.menglin.invest.entity.User;
import com.menglin.invest.util.PageBean;

public interface IUserService {
	public List<User> findAll();
    public void saveUser(User book);
    public User findOne(int id);
    public void delete(int id);
    public PageBean<User> findPageBycondition(int page,int size, User model);
    
      

}
