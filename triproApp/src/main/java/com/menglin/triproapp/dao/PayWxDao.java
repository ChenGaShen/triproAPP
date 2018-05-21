package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.entity.Validate;

public interface PayWxDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PayWx record);

    int insertSelective(PayWx record);

    PayWx selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayWx record);

    int updateByPrimaryKey(PayWx record);
    
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
    List<PayWx> findByPage(HashMap<String,Object> map);
}