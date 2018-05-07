package com.menglin.tripro.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.menglin.tripro.entity.Admin;
import com.menglin.tripro.entity.Role;

public interface AdminDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
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
    List<Admin> findByPage(HashMap<String,Object> map);
    
    Admin selectByNameAndPass(HashMap<String,Object> map);
    Admin selectByName(String adminName);
}