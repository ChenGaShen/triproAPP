package com.menglin.invest.service;

import java.util.List;
import com.menglin.invest.entity.Questions;


public interface IQuestionsService {
	public List<Questions> findAll();
    public void saveQuestions(Questions book);
    public Questions findOne(int id);
    public void delete(int id);
}
