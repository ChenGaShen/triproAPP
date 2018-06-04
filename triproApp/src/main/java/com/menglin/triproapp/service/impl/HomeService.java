package com.menglin.triproapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.AdressDao;
import com.menglin.triproapp.dao.CommodityDao;
import com.menglin.triproapp.dao.HomeDao;
import com.menglin.triproapp.dao.UserDao;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.Home;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IHomeService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;

/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:48:12 
 */
@Service("homeService")
public class HomeService implements IHomeService {
	
	@Resource
	private  HomeDao homeDao;
	
	
	
	
	@Override
	public void deleteByPrimaryKey(Integer id) {
		
		homeDao.deleteByPrimaryKey(id);

	}

	@Override
	public void save(Home record) {
		homeDao.insertSelective(record);

	}

	@Override
	public Home get(Integer id) {
		
		return homeDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Home record) {
		homeDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Home> selectHomeList() {
		  HashMap<String,Object> map = new HashMap<String,Object>();
		List<Home> homes = homeDao.selectHomeList(map);
        if (CheckData.isNotNullOrEmpty(homes)) {
        	for (int i = 0; i < homes.size(); i++) {
        		if (CheckData.isNotNullOrEmpty(homes.get(i).getImg())) {
        			homes.get(i).setImg(SystemParam.DOMAIN_NAME+homes.get(i).getImg());
				}
			}
        	
		}
        return homes;
	}
	

	@Override
	public PageBean<Home> findByPage(Integer currentPage, Integer pageSize, Home model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("userPhone", model.getUserPhone());
        //总记录数
        int totalCount = homeDao.selectCount(map1);
        
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
//        map.put("userPhone", model.getUserPhone());
       
        List<Home> lists = homeDao.findByPage(map);
        PageBean<Home> pageBean = new PageBean<Home>(currentPage, pageSize,totalCount);
        if (CheckData.isNotNullOrEmpty(lists)) {
        	for (int i = 0; i < lists.size(); i++) {
        		if (CheckData.isNotNullOrEmpty(lists.get(i).getImg())) {
        			lists.get(i).setImg(SystemParam.DOMAIN_NAME+lists.get(i).getImg());
				}
			}
        	 pageBean.setPageList(lists);
        	
		}
        return pageBean;
	}

	

}
