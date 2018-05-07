package com.menglin.invest.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.InformationDao;
import com.menglin.invest.entity.Information;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.IinformationService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.DateUntil;
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
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("newsClassification", information.getNewsClassification());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = informationDao.selectCount(map1);
        
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
        map.put("newsClassification", information.getNewsClassification());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<Information> lists = informationDao.findByPage(map);
        
        PageBean<Information> pageBean = new PageBean<Information>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	
	

}
