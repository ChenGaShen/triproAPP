package com.menglin.tripro.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.tripro.entity.Message;


public interface MessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
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
    List<Message> findByPage(HashMap<String,Object> map);
}