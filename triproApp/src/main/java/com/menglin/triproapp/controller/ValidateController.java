package com.menglin.triproapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.Validate;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.PageRuslt;

/** 
 * @author CGS 
 * @date 2018年3月8日 上午9:58:37 
 */
@Controller  
@RequestMapping("/admin/validate")
public class ValidateController {

	
	@Resource  
    private IValidateService validateService;
	
	/**
	 * 分页查询注册时验证码信息
	 * @author CGS
	 * @time 2018年3月8日上午10:08:00
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return.'
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Validate> findByPage(Integer currentPage, Integer pageSize,Validate model,String startTime,String endTime){
		System.out.println("findByPage 方法");
		System.out.println(SystemParam.DOMAIN_NAME);
		PageRuslt<Validate> pageRuslt =new PageRuslt<Validate>();
		PageBean<Validate> PageUser=validateService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
}
