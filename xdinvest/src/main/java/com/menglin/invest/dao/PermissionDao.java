package com.menglin.invest.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.invest.entity.Permission;


public interface PermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);
    
    List<Permission> selectByAdmin(Integer adminId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
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
    List<Permission> findByPage(HashMap<String,Object> map);
}