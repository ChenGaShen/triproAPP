package com.menglin.invest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.impl.UserServiceImpl;


@RestController
@ComponentScan

@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		System.out.println("------------SpringBoot����controller����------------------");
		System.out.println("88888888888888");
		return "你好，Spring Boot";
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doRegister")
	public String doRegister(User user) {
		System.out.println("------------SpringBoot注册------------------");
		Map<String,String> map=new HashMap<String,String>();
		user.setOnState(0);
		user.setAddTime(new Date());
		userService.saveUser(user);
		map.put("message","注册成功");
		return JSONObject.toJSONString(map);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findOneById")
	public String findOneById(int uid) {
		System.out.println("------------SpringBoot查询单个信息------------------");
		Map<String,Object> map=new HashMap<String,Object>();
		User user=userService.findOne(uid);
		map.put("user", user);
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll() {
		System.out.println("------------SpringBoot查询所有列表 ------------------");
		Map<String,Object> map=new HashMap<String,Object>();
		List<User> userList=userService.findAll();
		map.put("user", userList);
		return JSONObject.toJSONString(map);
	}
	/**
	 * 条件查询分页
	 * @param page
	 * @param size
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findPageBycondition")
	public String findPage(int page ,int size,User model) {
		System.out.println("------------SpringBoot分页方法-----------------");
		System.out.println("pageNumber:"+page);
		System.out.println("pageSize:"+size);
//		PageBean<User> pageBean =userService.findPageBycondition(page, size, model);
		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("userPage", pageBean);
		return JSONObject.toJSONString(map);
	}
	
}
