package com.menglin.invest.service.impl;

import java.util.List;

import com.menglin.invest.entity.Role;
import com.menglin.invest.util.PageBean;


public interface IRoleService   {
	public Role get(Integer id);
	public void save(Role role);
	public void delete(Integer id);
	public void update(Role role);
	public List<Role> findUserByAdmin(Integer adminId);
	public List<Role> selectUserList();
	PageBean<Role> findByPage(Integer currentPage,Integer pageSize,Role model);
}
