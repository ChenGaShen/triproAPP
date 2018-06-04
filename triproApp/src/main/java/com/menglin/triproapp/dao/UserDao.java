package com.menglin.triproapp.dao;


import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.User;

public interface UserDao  {
	
	 /**
     * 根据主键删除一条用户数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    
    
    /**
     * 插入一条完整用户数据
     * @param record
     * @return
     */
    int insert(User record);
    
    /**
     * 插入一条用户数据 ，可以不完整
     * @param record
     * @return
     */
    int insertSelective(User record);
    
    /**
     * 根据主键查询一条用户数据
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);
    
    /**
     * 根据手机号查询一条用户数据
     * @param userPhone
     * @return
     */
    public User findUserByPhone(String userPhone);
    
    public User findUserByOpenId(String openId);
    /**
     * 根据主键更新一条用户数据，可以不完整 -
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
    
    /**
     * 根据主键更新一条用户数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    
    /**
     * 查询所有用户数据
     * @return
     */
    List<User> selectUserList();
    
    /**
     * 查询用户记录总数(条件)
     * @return
     */
    int selectCount(HashMap<String,Object> map);
    
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<User> findByPage(HashMap<String,Object> map);
}