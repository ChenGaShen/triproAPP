package com.menglin.invest.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.UserDao;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.IUserService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.DateUntil;
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
		
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("userPhone", model.getUserPhone());
        map1.put("userName", model.getUserName());
        map1.put("idCard", model.getIdCard());
        map1.put("onState", model.getOnState());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = userDao.selectCount(map1);
        
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
        map.put("userPhone", model.getUserPhone());
        map.put("userName", model.getUserName());
        map.put("idCard", model.getIdCard());
        map.put("onState", model.getOnState());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<User> lists = userDao.findByPage(map);
        
        PageBean<User> pageBean = new PageBean<User>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
		
	}


	
	

	
	

}
