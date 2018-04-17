package com.menglin.invest.dao;

import com.menglin.invest.entity.UserFeedback;

public interface UserFeedbackDao {
    int deleteByPrimaryKey(Integer userFedId);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(Integer userFedId);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);
}