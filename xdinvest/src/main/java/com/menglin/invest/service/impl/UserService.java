package com.menglin.invest.service.impl;


import java.util.HashMap;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.UserDao;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.IUserService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;


@Service("userService")
public class UserService implements IUserService {
	
	
	@Resource  
    private UserDao userDao;

	@Override
	public User get(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public void save(User user) {
		userDao.insertSelective(user);
		
	}

	@Override
	public void delete(int userId) {
		userDao.deleteByPrimaryKey(userId);
		
	}

	@Override
	public void update(User user) {
		userDao.updateByPrimaryKeySelective(user);
		
	}

	/**
	 * 用户唯一性判断
	 */
	@Override
	public boolean unique(String userPhone) {
		boolean flag=false;
		User exig_user =userDao.judgeUser(userPhone);
		if(CheckData.isNotNullOrEmpty(exig_user)){
			flag=true;
		}
		return flag;
	}
	
	@Override
	public User checkLogin(String userPhone,String userPass) {
		  HashMap<String,Object> map = new HashMap<String,Object>();
		  map.put("userPhone", userPhone);
		  map.put("userPass", userPass);
		  User ch_user =  userDao.selectByNameAndPass(map);
		return ch_user;
	}
	
	@Override
	public PageBean<User> findByPage(Integer currentPage, Integer pageSize, User model, String startTime,String endTime) {
		
		return null;
	}


	
	

	
	

}
