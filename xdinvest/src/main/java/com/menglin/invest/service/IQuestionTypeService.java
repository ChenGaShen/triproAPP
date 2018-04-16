package com.menglin.invest.service;

import java.util.List;
import com.menglin.invest.entity.QuestionType;


public interface IQuestionTypeService {
	public List<QuestionType> findAll();
    public void saveQuestionType(QuestionType book);
    public QuestionType findOne(int id);
    public void delete(int id);
}
