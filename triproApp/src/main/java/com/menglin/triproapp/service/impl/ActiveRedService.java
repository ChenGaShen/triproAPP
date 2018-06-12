package com.menglin.triproapp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.ActiveRedDao;
import com.menglin.triproapp.dao.AdressDao;
import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.DateUntil;
import com.menglin.triproapp.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年5月28日 下午3:55:32 
 */
@Service("activeRedService")
public class ActiveRedService implements IActiveRedService {

	
	@Resource
	private  ActiveRedDao activeRedDao;
	
	@Override
	public void delete(Integer id) {
		activeRedDao.deleteByPrimaryKey(id);

	}

	@Override
	public void save(ActiveRed record) {
		activeRedDao.insertSelective(record);

	}

	@Override
	public ActiveRed get(Integer id) {
	
		return activeRedDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(ActiveRed record) {
		activeRedDao.updateByPrimaryKeySelective(record);

	}
	
	@Override
	public List<ActiveRed> activeRedList(Integer uid) {
		
		return activeRedDao.selectActiveRedList(uid);
	}

	@Override
	public PageBean<ActiveRed> findByPage(Integer currentPage, Integer pageSize, ActiveRed model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("loginName", model.getLoginName());
        //总记录数
        int totalCount = activeRedDao.selectCount(map1);
        
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
       
        List<ActiveRed> lists = activeRedDao.findByPage(map);
        
        PageBean<ActiveRed> pageBean = new PageBean<ActiveRed>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	@Override
	public ActiveRed selectByorderId(String orderId) {
		
		return activeRedDao.selectByorderId(orderId);
	}

	

}
