package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Role;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.util.PageBean;

public interface IRoleService   {
	public Role get(Integer id);
	public void save(Role role);
	public void delete(Integer id);
	public void update(Role role);
	public List<Role> findUserByAdmin(Integer adminId);
	public List<Role> selectUserList();
	PageBean<Role> findByPage(Integer currentPage,Integer pageSize,Role model);
}
