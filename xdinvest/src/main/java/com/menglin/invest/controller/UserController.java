package com.menglin.invest.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.menglin.invest.service.impl.UserService;


@Controller  
@RequestMapping("/xdadmin/user")
public class UserController {
	
	@Resource 
	private UserService userService;
	
	
	
}
