package com.menglin.triproapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.UserDao;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.DateUntil;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.OrederDetailVO;
import com.menglin.triproapp.vo.UserVO;

@Service("userService")
public class UserService  implements IUserService {
	
	@Resource  
    private UserDao userDao;
	
	@Override
	public User get(int userId) {
		  return this.userDao.selectByPrimaryKey(userId);
	}


	@Override
	public void save(User user) {
		this.userDao.insert(user);
		
	}

	@Override
	public void delete(int id) {
		this.userDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public void update(User user) {
		userDao.updateByPrimaryKey(user);
		
	}

	@Override
	public List<User> selectUserList() {
//		userDao.deleteByPrimaryKey(id)
		return null;
	}


	@Override
	public PageBean<User> findByPage(Integer currentPage, Integer pageSize,User model,String startTime,String endTime) {
		
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("loginName", model.getLoginName());
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
        map.put("loginName", model.getLoginName());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<User> lists = userDao.findByPage(map);
        
        PageBean<User> pageBean = new PageBean<User>(currentPage, pageSize,totalCount);
        
        
        pageBean.setPageList(lists);

        return pageBean;
	}


	@Override
	public User findUserByPhone(String userPhone) {
		
		return userDao.findUserByPhone(userPhone);
	}

	
	@Override
	public User findUserByOpenId(String openId) {
		return  userDao.findUserByOpenId(openId);
	}

	@Override
	public List<UserVO> outExport(User model, String startTime, String endTime) {

		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("loginName", model.getLoginName());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<User> lists = userDao.findByPage(map);
        
        List<UserVO> userVOs =new ArrayList<UserVO>();
			
	        // 循环遍历出用户所有内容
	        if (CheckData.isNotNullOrEmpty(lists)) {
	        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < lists.size(); i++) {
						UserVO vo =new UserVO();
						vo.setUid(lists.get(i).getUserId());
						vo.setLoginName(lists.get(i).getLoginName());
						vo.setState(lists.get(i).getState());
						vo.setPhoneBelong(lists.get(i).getPhoneBelong());
						vo.setAddTime(lists.get(i).getAddTime());
						vo.setLoginTime(lists.get(i).getLoginTime());
						vo.setRemark(lists.get(i).getRemark());
					 userVOs.add(vo);
				}
			}
		return userVOs;
	}


}
