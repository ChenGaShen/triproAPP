package com.menglin.invest.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.invest.entity.User;

public interface UserDao {
	
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User judgeUser(String userPhone);
    
    User selectByNameAndPass(HashMap<String,Object> map);
    
    int selectCount(HashMap<String,Object> map);
    
    List<User> findByPage(HashMap<String,Object> map);
    
    
}