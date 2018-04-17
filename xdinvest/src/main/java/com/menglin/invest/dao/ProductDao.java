package com.menglin.invest.dao;

import java.util.HashMap;
import java.util.List;
import com.menglin.invest.entity.Product;


public interface ProductDao {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    int selectCount(HashMap<String,Object> map);
    
    List<Product> findByPage(HashMap<String,Object> map);
}