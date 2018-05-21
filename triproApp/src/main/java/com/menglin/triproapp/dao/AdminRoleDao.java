package com.menglin.triproapp.dao;

import java.util.HashMap;

import com.menglin.triproapp.entity.AdminRole;

public interface AdminRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
    
    AdminRole selectByRoleAndAdmin(HashMap<String,Object> map);
    
}