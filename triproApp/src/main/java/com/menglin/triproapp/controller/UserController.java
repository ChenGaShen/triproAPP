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

import com.menglin.triproapp.entity.User;
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
	 * 供销商信息新增
	 * @author CGS
	 * @time 2018年2月7日下午3:46:02
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUserInfo.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN addUserInfo(User model){
		ResultVN vn =new ResultVN();
		if (CheckData.isNotEmptyString(model.getUserPhone())) {
			User vo = userService.findUserByPhone(model.getUserPhone());
			if (!CheckData.isNotNullOrEmpty(vo)) {
				 User user= new User();
				if (CheckData.isNotNullOrEmpty(model.getRemark())) {
					user.setRemark(model.getRemark());
				}
				if (CheckData.isNotNullOrEmpty(model.getLoginName())) {
					user.setLoginName(model.getLoginName());
				}
				if (CheckData.isNotNullOrEmpty(model.getIdCard())) {
					user.setIdCard(model.getIdCard());
				}
				String belong = null;
				try {
					belong = PhoneUtils.getBelonging(model.getUserPhone());
				} catch (Exception e) {
					e.printStackTrace();
				}
				user.setPhoneBelong(belong);
				user.setUserPhone(model.getUserPhone());
				user.setAudit(3);//审核状态1未审核2审核中3已审核4未通过
				user.setIdentity(2);//用户身份：1个人用户2供销商
				user.setState(1);// 用户状态为1正常2禁用
				String token = UUID.randomUUID().toString().replaceAll("\\-", "");
				user.setToken(token);
				user.setAddTime(new Date());
				userService.save(user);
				vn.setResult(Result.suc("新增成功!!"));
			}else{
				vn.setResult(Result.fal("账户已存在!!"));
			}
		}else{
			vn.setResult(Result.fal("手机号不能为空!!"));
		}
		 return  vn;
       
	}
	
	 /**
	  * 用户详情
	  * @author CGS
	  * @time 2018年3月14日上午9:46:01
	  * @param request
	  * @param id
	  * @return
	  */
	@RequestMapping("/showUser.json")  
   public @ResponseBody UserVO showUser(Integer uid){
       User user = userService.get(uid);
       UserVO userVO =new UserVO();
       if (CheckData.isNotNullOrEmpty(user)) {
    	   
    	   userVO.setUid(user.getId());
    	   userVO.setUserPhone(user.getUserPhone());
    	   userVO.setState(user.getState());
    	   userVO.setPhoneBelong(user.getPhoneBelong());
    	   userVO.setLoginTime(user.getLoginTime());
    	   userVO.setAddTime(user.getAddTime());
    	   userVO.setIdentity(user.getIdentity());
    	   userVO.setAudit(user.getAudit());
    	   userVO.setIdCard(user.getIdCard());
    	   if (CheckData.isNotNullOrEmpty(user.getIdCardImg())) {
    		   userVO.setIdCardImg(SystemParam.DOMAIN_NAME+user.getIdCardImg());
    	   }
    	   if (CheckData.isNotNullOrEmpty(user.getIdCardImgB())) {
    		   userVO.setIdCardImgB(SystemParam.DOMAIN_NAME+user.getIdCardImgB());
    	   }
    	   if (CheckData.isNotNullOrEmpty(user.getBusinessImg())) {
    		   userVO.setBusinessImg(SystemParam.DOMAIN_NAME+user.getBusinessImg());
		   }
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
			if (CheckData.isNotNullOrEmpty(model.getAudit())) {
				if (model.getAudit()==3) {//审核状态1未审核2审核中3已审核4未通过
					user.setIdentity(2);//用户身份：1个人用户2供销商
				}else{
					user.setIdentity(1);//用户身份：1个人用户2供销商
				}{
				}
				user.setAudit(model.getAudit());
			}
			if (CheckData.isNotNullOrEmpty(model.getState())) {
				user.setState(model.getState());
			}
			if (CheckData.isNotNullOrEmpty(model.getIdCard())) {
				user.setIdCard(model.getIdCard());
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
