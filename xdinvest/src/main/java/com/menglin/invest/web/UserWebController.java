package com.menglin.invest.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.IUserService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.MD5;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.UserVO;

/**
 * @author CGS
 * @time 2018年1月31日下午1:51:03
 */
@Controller  
@RequestMapping("/xdweb/user")
public class UserWebController {
	
	
	@Resource  
    private IUserService userService;
	
		/**
		 * 用户注册
		 * @author CGS
		 * @time 2018年4月17日下午1:50:03
		 * @param user
		 * @return
		 */
		@RequestMapping(value="/doRegister.json",method = {RequestMethod.POST})
		public @ResponseBody UserVO register(User user) {
			UserVO userVO = new UserVO();
			boolean isMobile = CheckData.isMobilephone(user.getUserPhone());
			if (isMobile) {
				boolean isOnly =userService.unique(user.getUserPhone());
					if (!isOnly) {
						user.setUserPass(MD5.md5(user.getUserPass()));
						//identity 默认为0 0新用户注册1个人客户2机构客户3客户经理
						userService.save(user);
						userVO.setResult(Result.suc("注册成功"));
					}else{
						userVO.setResult(Result.fal("账户已存在!"));
					}
			}else{
				userVO.setResult(Result.fal("手机格式错误!"));
			}
			return  userVO;
		
		}
	 
		/**
		 * 用户登录
		 * @author CGS
		 * @time 2018年4月17日下午2:02:45
		 * @param user
		 * @return
		 */
		@RequestMapping(value="/doLogin.json",method = {RequestMethod.POST})
		public @ResponseBody UserVO doLogin(User user) {
			UserVO userVO = new UserVO();
			if (CheckData.isNotEmptyString(user.getUserPhone()) && CheckData.isNotEmptyString(user.getUserPass())) {
				User ch_user =userService.checkLogin(user.getUserPhone(), MD5.md5(user.getUserPass()));
					if (CheckData.isNotNullOrEmpty(ch_user)) {
							 if (ch_user.getOnState()==0) { //使用状态 0使用 1未使用
								 	userVO.setUserName(ch_user.getUserName());
									userVO.setUserPhone(ch_user.getUserPhone());
									userVO.setIdentity(ch_user.getIdentity());
									userVO.setUserId(ch_user.getUserId());
									userVO.setAddTime(ch_user.getAddTime());
									userVO.setIdCard(ch_user.getIdCard());
									userVO.setResult(Result.suc("登录成功!"));
							}else{
								userVO.setResult(Result.fal("账号禁用!!"));
							}
						
					}else{
						userVO.setResult(Result.fal("账号或密码错误!"));
					}
			}else{
				userVO.setResult(Result.fal("账户或者密码不能为空"));
			}
			return userVO;
		}
	
	
}
