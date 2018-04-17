package com.menglin.invest.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.InformationDao;
import com.menglin.invest.entity.Information;
import com.menglin.invest.service.IinformationService;
import com.menglin.invest.util.PageBean;



@Service("informationService")
public class InformationService implements IinformationService {
	
	@Resource
	private InformationDao informationDao;

	@Override
	public Information get(int newsId) {
		return informationDao.selectByPrimaryKey(newsId);
	}

	@Override
	public void save(Information information) {
		informationDao.insertSelective(information);
		
	}

	@Override
	public void delete(int newsId) {
		informationDao.deleteByPrimaryKey(newsId);
		
	}

	@Override
	public void update(Information information) {
		informationDao.updateByPrimaryKeySelective(information);
		
	}

	@Override
	public PageBean<Information> findByPage(Integer currentPage, Integer pageSize, Information information,
			String startTime, String endTime) {
	
		return null;
	}

	
	

}
