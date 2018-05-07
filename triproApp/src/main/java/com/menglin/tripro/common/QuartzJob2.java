package com.menglin.tripro.common;

import java.util.Date;

public class QuartzJob2 {
	public void execute(){


        System.out.println("Quartz2------的任务调度！！！"+(new Date()).toString());

    }
}
