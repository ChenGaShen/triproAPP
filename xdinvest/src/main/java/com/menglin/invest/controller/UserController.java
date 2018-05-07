package com.menglin.invest.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.impl.UserService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.PageRuslt;


@Controller  
@RequestMapping("/xdadmin/user")
public class UserController {
	
	@Resource 
	private UserService userService;
	
	/**
	 * 用户分页查询 正式
	 * @author CGS
	 * @time 2018年3月14日上午9:40:08
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<User> findByPage(Integer currentPage, Integer pageSize,User model,String startTime,String endTime,HttpServletRequest request){
		
		PageRuslt<User> pageRuslt =new PageRuslt<User>();
		PageBean<User> PageUser=userService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	/**
	 * 用户信息状态修改
	 * @author CGS
	 * @time 2018年2月7日下午3:46:02
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUserInfo.json",method={RequestMethod.POST})  
    public @ResponseBody Result updateUserInfo(User model){
		User user =userService.get(model.getUserId());
		if (CheckData.isNotNullOrEmpty(user)) {
			if (CheckData.isNotNullOrEmpty(model.getOnState())) {
				user.setOnState(model.getOnState());
				userService.update(user);
			    return Result.suc("修改成功!!");
			}
		}
      return Result.fal("修改失败!!");
       
	}
	
}
