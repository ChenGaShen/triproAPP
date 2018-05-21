package com.menglin.triproapp.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.Adress;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.ResultListVN;


/** 
 * @author CGS 
 * @date 2018年2月1日 上午11:39:31 
 */
@Controller  
@RequestMapping("/web/commodity")
public class CommodityWebController {
	
	
	@Resource  
    private ICommodityService commodityService;
	
	/**
	 * 商品列表
	 * @author CGS
	 * @time 2018年2月2日下午3:51:44
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value="myCache",key="#commodityList")
	@RequestMapping(value="/commodityList",method = {RequestMethod.POST})
	public @ResponseBody ResultListVN<CommodityDetailVO> commodityList(Integer uid) throws IOException {

		ResultListVN<CommodityDetailVO> rs=new ResultListVN<CommodityDetailVO>();
		
	
				
			List<CommodityDetailVO> commodityList=commodityService.selectCommodityList(uid);
			
			if (null==commodityList) {
				rs.setResult(Result.fal("暂无商品信息!!"));
				 return rs;
			}else{
				rs.setResultList(commodityList);
				rs.setResult(Result.suc("查询成功!!"));
				return rs;
			}
		
	}
}
