package com.menglin.invest.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.QuestionsDao;
import com.menglin.invest.entity.Questions;
import com.menglin.invest.service.IQuestionsService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.DateUntil;
import com.menglin.invest.util.PageBean;


@Service("questionsService")
public class QuestionsService implements IQuestionsService {
	
	
	
	@Resource
	private QuestionsDao questionsDao;

	@Override
	public Questions get(int questionId) {
		
		return questionsDao.selectByPrimaryKey(questionId);
	}

	@Override
	public void save(Questions questions) {
		questionsDao.insertSelective(questions);
	}

	@Override
	public void delete(int questionId) {
		questionsDao.deleteByPrimaryKey(questionId);
		
	}

	@Override
	public void update(Questions questions) {
		questionsDao.updateByPrimaryKeySelective(questions);
		
	}

	@Override
	public PageBean<Questions> findByPage(Integer currentPage, Integer pageSize, Questions questions, String startTime,
			String endTime) {
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("questionClassification", questions.getQuestionClassification());
        map1.put("questionState", questions.getQuestionState());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = questionsDao.selectCount(map1);
        
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
        map.put("questionClassification", questions.getQuestionClassification());
        map.put("questionState", questions.getQuestionState());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<Questions> lists = questionsDao.findByPage(map);
        
        PageBean<Questions> pageBean = new PageBean<Questions>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	
	

}
