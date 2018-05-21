package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.Role;


public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    
    List<Role> selectByAdmin(Integer adminId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    
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
    List<Role> findByPage(HashMap<String,Object> map);
}