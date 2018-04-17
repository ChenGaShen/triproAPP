package com.menglin.invest.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.UserFeedbackDao;
import com.menglin.invest.entity.UserFeedback;
import com.menglin.invest.service.IUserFeedBackService;
import com.menglin.invest.util.PageBean;

@Service("userFeedBackService")
public class UserFeedBackService implements IUserFeedBackService {
	
	
	@Resource
	private UserFeedbackDao userFeedBackDao;

	@Override
	public UserFeedback get(int userFedId) {
		
		return userFeedBackDao.selectByPrimaryKey(userFedId);
	}

	@Override
	public void save(UserFeedback userFeedback) {
		userFeedBackDao.insertSelective(userFeedback);
		
	}

	@Override
	public void delete(int userFedId) {
		userFeedBackDao.deleteByPrimaryKey(userFedId);
		
	}

	@Override
	public void update(UserFeedback userFeedback) {
		userFeedBackDao.updateByPrimaryKeySelective(userFeedback);
		
	}

	@Override
	public PageBean<UserFeedback> findByPage(Integer currentPage, Integer pageSize, UserFeedback userFeedback,
			String startTime, String endTime) {
		
		return null;
	}

	
	
	
}
