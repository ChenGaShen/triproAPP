package com.menglin.tripro.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.menglin.tripro.entity.Role;
import com.menglin.tripro.service.IRoleService;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.Result;
import com.menglin.tripro.util.SystemParam;
import com.menglin.tripro.vo.PageRuslt;
import com.menglin.tripro.vo.ResultVN;


/** 
 * @author CGS 
 * @date 2018年3月1日 下午4:04:18 
 */
@Controller  
@RequestMapping("/admin/role")
public class RoleController {

	
	@Resource  
    private IRoleService roleService;
	
	/**
	 * 分页查询角色信息
	 * @author CGS
	 * @time 2018年3月5日下午2:12:05
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Role> findByPage(int currentPage, int pageSize,Role model){
		PageRuslt<Role> pageRuslt =new PageRuslt<Role>();
		PageBean<Role> PageUser=roleService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	/**
	 * 新增角色信息
	 * @author CGS
	 * @time 2018年3月5日下午2:12:24
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addRole.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN addRole(Role model){
		ResultVN vn =new ResultVN();
		roleService.save(model);
		vn.setResult(Result.suc("新角色添加成功!"));
      return vn;
	}
	
	/**
	 * 角色信息修改
	 * @author CGS
	 * @time 2018年3月5日下午3:30:08
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateRole.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateRole(Role model){
		ResultVN vn =new ResultVN();
		roleService.update(model);
		vn.setResult(Result.suc("修改成功!"));
      return vn;
	}
	
	
	/**
	 * 删除角色信息
	 * @author CGS
	 * @time 2018年3月5日下午2:12:45
	 * @param rid
	 * @return
	 */
	@RequestMapping(value="/delRole.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN delRole(Integer roleId){
		ResultVN vn =new ResultVN();
		roleService.delete(roleId);
		vn.setResult(Result.suc("删除成功!"));
      return vn;
	}
	
}
