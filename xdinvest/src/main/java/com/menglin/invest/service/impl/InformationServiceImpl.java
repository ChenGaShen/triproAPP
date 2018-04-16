package com.menglin.invest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menglin.invest.dao.InformationDao;
import com.menglin.invest.entity.Information;
import com.menglin.invest.service.InformationService;



@Service
@Transactional
public class InformationServiceImpl implements InformationService {
	
	@Autowired
	private InformationDao informationDao;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
//		informationDao.delete(id);
	}
	
	@Override
	public List<Information> findAll() {
//		return informationDao.findAll();
		return null;
	}

	@Override
	public List<Information> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Information findOne(int id) {
		
//		return informationDao.findOne(id);
		return null;
	}

	@Override
	public void saveInformation(Information book) {
//		informationDao.save(book);

	}

}
