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
import com.menglin.triproapp.entity.CommodityDetails;
import com.menglin.triproapp.entity.Home;
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IHomeService;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;
import com.menglin.triproapp.vo.ResultListVN;
import com.menglin.triproapp.vo.ResultObject;


/** 
 * @author CGS 
 * @date 2018年2月1日 上午11:39:31 
 */
@Controller  
@RequestMapping("/web/wxindex")
public class IndexWebController {
	
	
	@Resource  
    private IHomeService homeService;
	
	


	@RequestMapping(value="/indexImg.json",method = {RequestMethod.POST})
	public @ResponseBody ResultListVN<Home> indexImg() throws IOException {

			ResultListVN<Home> rs=new ResultListVN<Home>();
		
			List<Home> homes=homeService.selectHomeList();
			
			if (CheckData.isNotNullOrEmpty(homes)){
				 	rs.setResultList(homes);
					rs.setResult(Result.suc("查询成功!!"));
			}else{
				 rs.setResult(Result.fal("暂无信息!!"));
			}
			return rs;
	}
	
}
