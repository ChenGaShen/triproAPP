package com.menglin.tripro.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.tripro.entity.Permission;
import com.menglin.tripro.service.IPermissionrService;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.Result;
import com.menglin.tripro.vo.PageRuslt;
import com.menglin.tripro.vo.ResultVN;

/** 
 * @author CGS 
 * @date 2018年3月1日 下午4:04:42 
 */
@Controller  
@RequestMapping("/admin/permission")
public class PermissionController {
		
	@Resource
	private IPermissionrService permissionrService;
	
	/**
	 * 分页查询所有权功能
	 * @author CGS
	 * @time 2018年3月5日下午2:04:30
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Permission> findByPage(int currentPage, int pageSize,Permission model){
		PageRuslt<Permission> pageRuslt =new PageRuslt<Permission>();
		PageBean<Permission> PageUser=permissionrService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	/**
	 * 添加权限功能
	 * @author CGS
	 * @time 2018年3月5日下午2:03:48
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addPermission.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN addPermission(Permission model){
		ResultVN vn =new ResultVN();
		permissionrService.save(model);
		vn.setResult(Result.suc("新权限功能添加成功!"));
      return vn;
	}
	
	/**
	 * 修改权限功能
	 * @author CGS
	 * @time 2018年3月5日下午2:15:57
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updatePermission.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updatePermission(Permission model){
		ResultVN vn =new ResultVN();
		permissionrService.update(model);
		vn.setResult(Result.suc("修改成功!"));
      return vn;
	}
	
	/**
	 * 删除权限功能
	 * @author CGS
	 * @time 2018年3月5日下午2:04:15
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/delPermission.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN delPermission(Integer pid){
		ResultVN vn =new ResultVN();
		permissionrService.delete(pid);
		vn.setResult(Result.suc("删除成功!"));
      return vn;
	}
	
}
