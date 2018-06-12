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
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.CommodityDetailImgVO;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;
import com.menglin.triproapp.vo.CommoditySeckillDetailVO;
import com.menglin.triproapp.vo.CommoditySeckillListInfo;
import com.menglin.triproapp.vo.ResultListVN;
import com.menglin.triproapp.vo.ResultObject;


/** 
 * @author CGS 
 * @date 2018年2月1日 上午11:39:31 
 */
@Controller  
@RequestMapping("/web/wxcommoditySeckill")
public class CommoditySeckillWebController {
	
	
	@Resource  
    private ICommoditySeckillService commoditySeckillService;
	
	
	@Resource  
    private ICommodityDetailImgService  commodityDetailImgService;
	
	/**
	 * 秒杀商品列表
	 * @author CGS
	 * @time 2018年2月2日下午3:51:44
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commoditySeckillList.json",method = {RequestMethod.POST})
	public @ResponseBody ResultListVN<CommoditySeckillListInfo> commoditySeckillList() throws IOException {

			ResultListVN<CommoditySeckillListInfo> rs=new ResultListVN<CommoditySeckillListInfo>();
		
			List<CommoditySeckillListInfo> commoditySeckillList=commoditySeckillService.selectCommoditySeckillList();
			if (CheckData.isNotNullOrEmpty(commoditySeckillList)){
				 	rs.setResultList(commoditySeckillList);
					rs.setResult(Result.suc("查询成功!!"));
			}else{
				 rs.setResult(Result.fal("暂无秒杀商品列表!!"));
			}
			return rs;
	}
	
	/**
	 * 商品详情
	 * @author CGS
	 * @time 2018年5月22日下午4:34:26
	 * @param commodityseckillId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commoditySeckillDetail.json",method = {RequestMethod.POST})
	public @ResponseBody ResultObject<CommoditySeckillDetailVO> commoditySeckillDetail(Integer commodityseckillId) throws IOException {
			ResultObject<CommoditySeckillDetailVO> rs=new ResultObject<CommoditySeckillDetailVO>();
			CommoditySeckillDetailVO commoditySeckillDetailVO =commoditySeckillService.selectCommoditySeckillDetail(commodityseckillId);
			if (CheckData.allfieldIsNotNUll(commoditySeckillDetailVO) ){
				 	rs.setObject(commoditySeckillDetailVO); //秒杀商品状态1上架0下架     商品秒杀状态0开启1关闭 && commoditySeckillDetailVO.getSeckillOnsale()==1 && commoditySeckillDetailVO.getSeckillState()==0
					rs.setResult(Result.suc("查询成功!!"));
			}else{
				 rs.setResult(Result.fal("暂无秒杀商品详情信息,或者此商品秒杀已结束!!"));
			}
			return rs;
	}
	
	/**
	 * 商品详情图
	 * @author CGS
	 * @time 2018年5月22日下午4:34:43
	 * @param commodityseckillId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commoditySeckillDetailImg.json",method = {RequestMethod.POST})
	public @ResponseBody ResultObject<String> commoditySeckillDetailImg(Integer commodityseckillId) throws IOException {
			ResultObject<String> rs=new ResultObject<String>();
			List<String> cImgList=commodityDetailImgService.findByCommoditySeckillId(commodityseckillId);
			if (CheckData.isNotNullOrEmpty(cImgList)) {
				rs.setObject(cImgList);
				rs.setResult(Result.suc("查询成功!!"));
			}else{
				rs.setResult(Result.fal("暂无商品详情图,或者此商品秒杀已结束!!"));
			}
			return rs;
	}
	

}
