package com.menglin.invest.service;

import java.util.List;

import com.menglin.invest.entity.UserFeedBack;

public interface IUserFeedBackService {
	public List<UserFeedBack> findAll();
    public void saveUserFeedBack(UserFeedBack book);
    public UserFeedBack findOne(int id);
    public void delete(int id);
}
