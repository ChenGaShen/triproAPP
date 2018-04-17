package com.menglin.invest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.menglin.invest.service.impl.UserFeedBackService;




@Controller  
@RequestMapping("/xdadmin/userFeedBack")
public class UserFeedBackController {
	@Autowired
	private UserFeedBackService userFeedBackService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		System.out.println("------------SpringBoot����controller����------------------");
		System.out.println("88888888888888");
		return "你好，Spring Boot";
	}
}
