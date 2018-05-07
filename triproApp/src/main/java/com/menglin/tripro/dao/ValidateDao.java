package com.menglin.tripro.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.tripro.entity.User;
import com.menglin.tripro.entity.Validate;

public interface ValidateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Validate record);

    int insertSelective(Validate record);

    Validate selectByPrimaryKey(Integer id);
    
    /**
     * 查询未验证的
     * @param id
     * @return
     */
    Validate selectByCoent(HashMap<String,Object> map);
    
    Validate doValidateCode(HashMap<String,Object> map);

    int updateByPrimaryKeySelective(Validate record);

    int updateByPrimaryKey(Validate record);
    
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
    List<Validate> findByPage(HashMap<String,Object> map);
}