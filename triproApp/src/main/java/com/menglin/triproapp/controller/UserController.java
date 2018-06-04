package com.menglin.triproapp.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.PhoneUtils;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;
import com.menglin.triproapp.vo.UserVO;

/**
 * @author CGS
 * @time 2018年2月1日下午1:34:12
 */
@Controller  
@RequestMapping("/admin/user")
public class UserController  {
	
	 	@Resource  
	    private IUserService userService;
	 	
		@Resource  
	    private IActiveRedService activeRedService;
	 	
		
	 	
	
	
	
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
    public @ResponseBody PageRuslt<User> findByPage(Integer currentPage, Integer pageSize,User model,String startTime,String endTime,HttpServletRequest request) throws UnsupportedEncodingException, IOException{
		
															//  页面数量                 页码
		PageRuslt<User> pageRuslt =new PageRuslt<User>();
		PageBean<User> PageUser=userService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	
	
	
	 /**
	  * 用户详情
	  * @author CGS
	  * @time 2018年3月14日上午9:46:01
	  * @param request
	  * @param id
	  * @return
	  */
	@RequestMapping(value="/showUser.json",method={RequestMethod.POST})  
   public @ResponseBody UserVO showUser(Integer uid){
       User user = userService.get(uid);
       UserVO userVO =new UserVO();
       if (CheckData.isNotNullOrEmpty(user)) {
    	   
    	   userVO.setUid(user.getUserId());
    	   userVO.setLoginName(user.getLoginName());
    	   userVO.setState(user.getState());
    	   userVO.setPhoneBelong(user.getPhoneBelong());
    	   userVO.setLoginTime(user.getLoginTime());
    	   userVO.setAddTime(user.getAddTime());
    	   userVO.setRemark(user.getRemark());
    	   userVO.setResult(Result.suc("查询成功!!"));
    	   return userVO;
       }else{
    	   userVO.setResult(Result.fal("查询失败!!"));
    	   return userVO;
    	 
       }
			
	}
	
	
	
	/**
	 * 用户信息状态修改
	 * @author CGS
	 * @time 2018年2月7日下午3:46:02
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUserInfo.json",method={RequestMethod.POST})  
    public @ResponseBody Result updateUserInfo(User model,Integer uid){
		
		User user =userService.get(uid);
		if (CheckData.isNotNullOrEmpty(user)) {
			if (CheckData.isNotNullOrEmpty(model.getRemark())) {
				user.setRemark(model.getRemark());
			}
			if (CheckData.isNotNullOrEmpty(model.getState())) {
				user.setState(model.getState());
			}
			userService.update(user);
	      return Result.suc("修改成功!!");
		}
      return Result.fal("修改失败!!");
       
	}
	
	
	
	
	
	// 导出excel表格操作
	@RequestMapping ( value="/outExport.json",method = {RequestMethod.POST})
	public @ResponseBody List<UserVO> outExport(User model,String startTime,String endTime){
		List<UserVO> reExcelVOs = userService.outExport(model,startTime,endTime);
		return reExcelVOs;
	}
			
	
	
}
