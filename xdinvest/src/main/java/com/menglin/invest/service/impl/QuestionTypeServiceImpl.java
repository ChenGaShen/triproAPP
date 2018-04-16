package com.menglin.invest.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menglin.invest.dao.QuestionTypeDao;
import com.menglin.invest.entity.QuestionType;
import com.menglin.invest.service.IQuestionTypeService;

@Service
@Transactional
public class QuestionTypeServiceImpl implements IQuestionTypeService {
	
	
	@Autowired
	private QuestionTypeDao questionTypeDao;
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuestionType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionType findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestionType(QuestionType book) {
		// TODO Auto-generated method stub

	}

}
