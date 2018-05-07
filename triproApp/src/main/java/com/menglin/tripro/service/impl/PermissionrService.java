package com.menglin.tripro.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.tripro.dao.PermissionDao;
import com.menglin.tripro.entity.Permission;

import com.menglin.tripro.service.IPermissionrService;
import com.menglin.tripro.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年2月24日 下午3:06:45 
 */
@Service("permissionrService")
public class PermissionrService implements IPermissionrService {

	@Resource  
    private PermissionDao permissionDao;
	
	@Override
	public Permission get(Integer id) {
		
		return permissionDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(Permission permission) {
		permissionDao.insertSelective(permission);
	}

	@Override
	public void delete(Integer id) {
		permissionDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Permission permission) {
		permissionDao.updateByPrimaryKeySelective(permission);

	}

	@Override
	public  List<Permission> findUserByAdmin(Integer adminId) {
		return permissionDao.selectByAdmin(adminId);
	}

	@Override
	public List<Permission> selectUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Permission> findByPage(Integer currentPage, Integer pageSize, Permission model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
       
        //总记录数
        int totalCount = permissionDao.selectCount(map1);
        
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
       
       
        List<Permission> lists = permissionDao.findByPage(map);
        
        PageBean<Permission> pageBean = new PageBean<Permission>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

}
