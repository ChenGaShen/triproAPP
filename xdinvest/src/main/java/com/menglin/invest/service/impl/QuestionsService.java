package com.menglin.invest.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.QuestionsDao;
import com.menglin.invest.entity.Questions;
import com.menglin.invest.service.IQuestionsService;
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
		
		return null;
	}

	
	

}
