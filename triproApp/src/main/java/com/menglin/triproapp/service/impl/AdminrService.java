package com.menglin.triproapp.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.AdminDao;
import com.menglin.triproapp.dao.AdminRoleDao;
import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.AdminRole;
import com.menglin.triproapp.service.IAdminrService;
import com.menglin.triproapp.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年2月24日 下午3:06:02 
 */
@Service("adminrService")
public class AdminrService implements IAdminrService {

	
	@Resource  
    private AdminDao adminDao;
	
	@Resource  
    private AdminRoleDao adminRoleDao;
	
	
	
	@Override
	public Admin get(Integer id) {
		
		return adminDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(Admin admin) {
		
		adminDao.insertSelective(admin);
	}

	@Override
	public void delete(Integer id) {
		adminDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Admin admin) {
		adminDao.updateByPrimaryKeySelective(admin);

	}

	@Override
	public Admin checkLogin(String adminName,String adminPass) {
		  HashMap<String,Object> map = new HashMap<String,Object>();
		  map.put("adminName", adminName);
		  map.put("adminPass", adminPass);
		  Admin admin =  adminDao.selectByNameAndPass(map);
		return admin;
	}

	@Override
	public List<Admin> selectUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Admin> findByPage(Integer currentPage, Integer pageSize, Admin model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
       
        //总记录数
        int totalCount = adminDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
       
       
        List<Admin> lists = adminDao.findByPage(map);
        
        PageBean<Admin> pageBean = new PageBean<Admin>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	@Override
	public Admin findByAdminName(String adminName) {
		
		return adminDao.selectByName(adminName);
	}
	
	/**
	 * 账户赋予角色
	 * @author CGS
	 * @time 2018年3月5日下午2:48:44
	 * @param rid
	 * @param aid
	 */
	@Override
	public void addEndowRole(Integer roleId,Integer adminId) {
		AdminRole  adminRole=new AdminRole();
		adminRole.setAdminId(adminId.toString());
		adminRole.setRoleId(roleId.toString());
		adminRoleDao.insert(adminRole);
	}
	
	/**
	 * 用户角色更新
	 * @author CGS
	 * @time 2018年3月5日下午3:07:35
	 * @param roleId
	 * @param adminId
	 */
	@Override
	public void updateEndowRole(Integer roleId,Integer adminId) {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId.toString());
		map.put("adminId", adminId.toString());
		AdminRole  adminRole=adminRoleDao.selectByRoleAndAdmin(map);
		if (null!=adminRole) {
			adminRole.setAdminId(adminId.toString());
			adminRole.setRoleId(roleId.toString());

			adminRoleDao.updateByPrimaryKey(adminRole);
		}
		
	}

}
