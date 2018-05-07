package com.menglin.tripro.common;

import java.util.Date;

import javax.annotation.Resource;

import com.menglin.tripro.service.ICommodityService;
import com.menglin.tripro.service.IOrderService;

public class QuartzJob1 {
	
	@Resource  
    private ICommodityService commodityService;
	
	@Resource  
    private IOrderService orderService;
	
	public void execute(){
		
		 System.out.println("Quartz1------的任务调度！！！"+(new Date()).toString());
    }
}
