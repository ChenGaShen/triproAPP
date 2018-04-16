package com.menglin.invest.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.menglin.invest.dao.RoleDao;
import com.menglin.invest.entity.Role;
import com.menglin.invest.service.IRoleService;
import com.menglin.invest.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年2月24日 下午3:06:27 
 */
@Service("roleService")
public class RoleService implements IRoleService {
	
	@Resource  
    private RoleDao roleDao;
	
	@Override
	public Role get(Integer id) {
		
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(Role role) {
		roleDao.insertSelective(role);

	}

	@Override
	public void delete(Integer id) {
		roleDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Role role) {
		roleDao.updateByPrimaryKeySelective(role);

	}

	@Override
	public List<Role> findUserByAdmin(Integer adminId){
		return roleDao.selectByAdmin(adminId);
	}

	@Override
	public List<Role> selectUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Role> findByPage(Integer currentPage, Integer pageSize,Role model) {
		
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
       
        //总记录数
        int totalCount = roleDao.selectCount(map1);
        
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
       
       
        List<Role> lists = roleDao.findByPage(map);
        
        PageBean<Role> pageBean = new PageBean<Role>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

}
