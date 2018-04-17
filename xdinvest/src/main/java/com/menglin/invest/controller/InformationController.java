package com.menglin.invest.controller;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.menglin.invest.service.impl.InformationService;


@Controller  
@RequestMapping("/admin/information")
public class InformationController {
	
	@Resource 
	private InformationService informationService ;
	
}
