package com.menglin.triproapp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.CommodityDetailImgVO;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;
import com.menglin.triproapp.vo.ResultListVN;
import com.menglin.triproapp.vo.ResultObject;


/** 
 * @author CGS 
 * @date 2018年2月1日 上午11:39:31 
 */
@Controller  
@RequestMapping("/web/wxcommodity")
public class CommodityWebController {
	
	
	@Resource  
    private ICommodityService commodityService;
	
	
	@Resource  
    private ICommodityDetailImgService  commodityDetailImgService;
	
	/**
	 * 商品列表
	 * @author CGS
	 * @time 2018年2月2日下午3:51:44
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commodityList.json",method = {RequestMethod.POST})
	public @ResponseBody ResultListVN<CommodityListInfo> commodityList(Integer condition,Integer classify,String commodityName) throws IOException {

			ResultListVN<CommodityListInfo> rs=new ResultListVN<CommodityListInfo>();
		
			List<CommodityListInfo> commodityList=commodityService.selectCommodityList(condition,classify,commodityName);
			
			if (CheckData.isNotNullOrEmpty(commodityList)){
				 	rs.setResultList(commodityList);
					rs.setResult(Result.suc("查询成功!!"));
			}else{
				 rs.setResult(Result.fal("暂无商品列表!!"));
			}
			return rs;
	}
	
	/**
	 * 商品详情
	 * @author CGS
	 * @time 2018年5月22日下午4:34:26
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commodityDetail.json",method = {RequestMethod.POST})
	public @ResponseBody ResultObject<CommodityDetailVO> commodityDetail(Integer commodityId) throws IOException {
			ResultObject<CommodityDetailVO> rs=new ResultObject<CommodityDetailVO>();
			CommodityDetailVO vo =new CommodityDetailVO();
			vo =commodityService.selectCommodityDetail(commodityId);
			if (CheckData.isNotNullOrEmpty(vo.getCommodityId())){
				 	rs.setObject(vo);
					rs.setResult(Result.suc("查询成功!!"));
			}else{
				 rs.setResult(Result.fal("暂无商品详情!!"));
			}
			return rs;
	}
	
	/**
	 * 商品详情图
	 * @author CGS
	 * @time 2018年5月22日下午4:34:43
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commodityDetailImg.json",method = {RequestMethod.POST})
	public @ResponseBody ResultObject<String> commodityDetailImg(Integer commodityId) throws IOException {
			ResultObject<String> rs=new ResultObject<String>();
			List<String> cImgList=commodityDetailImgService.findByCommodityId(commodityId);
			if (CheckData.isNotNullOrEmpty(cImgList)) {
				rs.setObject(cImgList);
				rs.setResult(Result.suc("查询成功!!"));
			}else{
				rs.setResult(Result.fal("暂无商品详情图!!"));
			}
			return rs;
	}
	

}
