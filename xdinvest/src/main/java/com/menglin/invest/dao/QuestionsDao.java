package com.menglin.invest.dao;

import com.menglin.invest.entity.Questions;

public interface QuestionsDao {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer questionId);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
}