package com.menglin.triproapp.common;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;

public class QuartzJob2 {
	

	@Resource  
    private ICommoditySeckillService commoditySeckillService;
	
	
	public void execute(){

		 System.out.println("Quartz2定时——关闭——秒杀活动！！！"+(new Date()).toString());
			
		 List<CommoditySeckill> list=commoditySeckillService.selectAllCommoditySeckill();
		    if(CheckData.isNotNullOrEmpty(list)){
		    	for (int i = 0; i < list.size(); i++) {
		    		if (list.get(i).getSeckillState()==0 && list.get(i).getSeckillOnsale()==0) { //商品秒杀状态0开启1关闭,开启状态，关闭
		    		System.out.println(Format.formatTest1(list.get(i).getEndTime(),1));
		    		if(Format.formatTest1(list.get(i).getEndTime(),1)){//15==多长时间
		    			CommoditySeckill seckill=commoditySeckillService.get(list.get(i).getCommodityseckillId());
						if (seckill.getCommodityseckillId()!=null) {
							seckill.setSeckillState(1);//商品秒杀状态0开启1关闭
							seckill.setSeckillOnsale(1);//商品状态0上架1下架,关闭同时，下架商品，不会在重复执行
							commoditySeckillService.update(seckill);
				          }
					}
		    		}
				}
		    }

    }
}
