package com.menglin.invest.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.UserFeedbackDao;
import com.menglin.invest.entity.User;
import com.menglin.invest.entity.UserFeedback;
import com.menglin.invest.service.IUserFeedBackService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.DateUntil;
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
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("userPhone", userFeedback.getUserPhone());
        map1.put("versions", userFeedback.getVersions());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = userFeedBackDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
        map.put("userPhone", userFeedback.getUserPhone());
        map.put("versions", userFeedback.getVersions());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<UserFeedback> lists = userFeedBackDao.findByPage(map);
        
        PageBean<UserFeedback> pageBean = new PageBean<UserFeedback>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
		
	}

	@Override
	public boolean deleteAll(int[] userFedIdS) {
		userFeedBackDao.deleteBySelective(userFedIdS);
		return false;
	}

	@Override
	public List<UserFeedback> outExport(UserFeedback model, String startTime, String endTime) {
		
		
	    //封装显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("userPhone", model.getUserPhone());
	        map.put("versions", model.getVersions());
	        map.put("startTime", startTime);
	        map.put("endTime", endTime);
	        List<UserFeedback> lists = userFeedBackDao.findByPage(map);
		
		return lists;
	}

	
	
	
}
