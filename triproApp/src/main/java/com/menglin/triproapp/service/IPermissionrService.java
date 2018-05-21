package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Permission;
import com.menglin.triproapp.util.PageBean;

public interface IPermissionrService   {
	public Permission get(Integer id);
	public void save(Permission permission);
	public void delete(Integer id);
	public void update(Permission permission);
	public List<Permission> findUserByAdmin(Integer adminId);
	public List<Permission> selectUserList();
	PageBean<Permission> findByPage(Integer currentPage,Integer pageSize,Permission model);
}
