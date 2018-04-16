package com.menglin.invest.service;

import java.util.List;
import com.menglin.invest.entity.Admin;
import com.menglin.invest.util.PageBean;

public interface IAdminrService   {
	public Admin get(Integer id);
	public void save(Admin admin);
	public void delete(Integer id);
	public void update(Admin admin);
	public Admin checkLogin(String adminName,String adminPass);
	public Admin findByAdminName(String adminName);
	public List<Admin> selectUserList();
	PageBean<Admin> findByPage(Integer currentPage,Integer pageSize,Admin model);
	void addEndowRole(Integer rid, Integer aid);
	void updateEndowRole(Integer roleId, Integer adminId);
}
