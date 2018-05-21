package com.menglin.triproapp.dao;

import com.menglin.triproapp.entity.RolePermission;

public interface RolePermissionDao{
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}