package com.menglin.invest.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.invest.entity.Questions;

public interface QuestionsDao {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer questionId);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
    
    int selectCount(HashMap<String,Object> map);
    
    List<Questions> findByPage(HashMap<String,Object> map);
}