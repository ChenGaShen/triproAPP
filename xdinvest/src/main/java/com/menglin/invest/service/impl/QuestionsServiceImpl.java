package com.menglin.invest.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menglin.invest.dao.QuestionsDao;
import com.menglin.invest.entity.Questions;
import com.menglin.invest.service.IQuestionsService;

@Service
@Transactional
public class QuestionsServiceImpl implements IQuestionsService {
	
	
	
	@Autowired
	private QuestionsDao questionsDao;
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Questions> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Questions findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestions(Questions book) {
		// TODO Auto-generated method stub

	}

}
