package com.menglin.invest.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menglin.invest.dao.UserFeedBackDao;
import com.menglin.invest.entity.UserFeedBack;
import com.menglin.invest.service.IUserFeedBackService;

@Service
@Transactional
public class UserFeedBackServiceImpl implements IUserFeedBackService {
	
	
	@Autowired
	private UserFeedBackDao userFeedBackDao;
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserFeedBack> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserFeedBack findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserFeedBack(UserFeedBack book) {
		// TODO Auto-generated method stub

	}

}
