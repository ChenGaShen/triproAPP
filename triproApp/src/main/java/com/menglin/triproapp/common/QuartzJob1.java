package com.menglin.triproapp.common;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IOrderService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.vo.CommoditySeckillListInfo;

public class QuartzJob1 {
	
	@Resource  
    private ICommoditySeckillService commoditySeckillService;
	
	
	public void execute(){
		 System.out.println("Quartz1定时——开启——秒杀活动！！！"+(new Date()).toString());
		
		 List<CommoditySeckill> list=commoditySeckillService.selectAllCommoditySeckill();
		    if(CheckData.isNotNullOrEmpty(list)){
		    	
		    	for (int i = 0; i < list.size(); i++) {
		    		if (list.get(i).getSeckillState()==1 && list.get(i).getSeckillOnsale()==0) { //商品状态0上架1下架
		    		System.out.println(Format.formatTest1(list.get(i).getStartTime(),1));
		    		if(Format.formatTest1(list.get(i).getStartTime(),1)){
		    			CommoditySeckill seckill=commoditySeckillService.get(list.get(i).getCommodityseckillId());
						if (seckill.getCommodityseckillId()!=null) {
							seckill.setSeckillState(0);//商品秒杀状态0开启1关闭
							commoditySeckillService.update(seckill);
				          }
					}
		    		}
				}
		    }
    }
}
