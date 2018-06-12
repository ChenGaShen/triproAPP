package com.menglin.triproapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.AdressDao;
import com.menglin.triproapp.dao.CommodityDao;
import com.menglin.triproapp.dao.CommoditySeckillDao;
import com.menglin.triproapp.dao.UserDao;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;
import com.menglin.triproapp.vo.CommoditySeckillDetailVO;
import com.menglin.triproapp.vo.CommoditySeckillListInfo;

/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:48:12 
 */
@Service("commoditySeckillService")
public class CommoditySeckillService implements ICommoditySeckillService {
	
	
	@Resource
	private  CommoditySeckillDao commoditySeckillDao;
	

	@Override
	public void deleteByPrimaryKey(Integer id) {
		commoditySeckillDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public void save(CommoditySeckill record) {
		commoditySeckillDao.insertSelective(record);
		
	}

	@Override
	public CommoditySeckill get(Integer id) {
		return commoditySeckillDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(CommoditySeckill record) {
		commoditySeckillDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void soldOutCommoditySeckill(Integer[] CommoditySeckillIds) {
		
		
	}

	@Override
	public PageBean<CommoditySeckillDetailVO> findByPage(Integer currentPage, Integer pageSize, CommoditySeckill model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("userPhone", model.getUserPhone());
        //总记录数
        int totalCount = commoditySeckillDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
//        map.put("userPhone", model.getUserPhone());
       
        List<CommoditySeckill> lists = commoditySeckillDao.findByPage(map);
        
        List<CommoditySeckillDetailVO> cDetailVOs=new ArrayList<CommoditySeckillDetailVO>();
        if (null!=lists) {
        	for (int i = 0; i < lists.size(); i++) {
        		CommoditySeckillDetailVO vo= new CommoditySeckillDetailVO();
    			vo.setCommodityseckillId(lists.get(i).getCommodityseckillId());
    			vo.setCommodityseckillName(lists.get(i).getCommodityseckillName());
    			vo.setSeckillPrice(Format.keepTwoMoney(lists.get(i).getSeckillPrice()));
    			vo.setSeckillDiscountprice(Format.keepTwoMoney(lists.get(i).getSeckillDiscountprice()));
    			vo.setSeckillAmount(lists.get(i).getSeckillAmount());
    			vo.setSeckillAllowance(lists.get(i).getSeckillAllowance());
    			vo.setSeckillCommodityimg(SystemParam.DOMAIN_NAME+lists.get(i).getSeckillCommodityimg());
    			vo.setSeckillSpecification(lists.get(i).getSeckillSpecification());
    			vo.setSeckillRealSale(lists.get(i).getSeckillRealSale());
    			vo.setSeckillVirtualSales(lists.get(i).getSeckillVirtualSales());
    			vo.setSeckillOnsale(lists.get(i).getSeckillOnsale());//是否出售
    			vo.setSeckillState(lists.get(i).getSeckillState());//是否开启秒杀
    			vo.setSeckillClassify(lists.get(i).getSeckillClassify());
    			vo.setAddTime(lists.get(i).getAddTime());
    			vo.setStartTime(lists.get(i).getStartTime());
    			vo.setEndTime(lists.get(i).getEndTime());
    			vo.setSeckillDescription(lists.get(i).getSeckillDescription());
    			cDetailVOs.add(vo);
			}
        	
		}
        
        PageBean<CommoditySeckillDetailVO> pageBean = new PageBean<CommoditySeckillDetailVO>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(cDetailVOs);

        return pageBean;
	}

	@Override
	public CommoditySeckillDetailVO selectCommoditySeckillDetail(Integer CommoditySeckillId) {
		CommoditySeckill commoditySeckill=commoditySeckillDao.selectByPrimaryKey(CommoditySeckillId);
		CommoditySeckillDetailVO vo=new CommoditySeckillDetailVO();
		if (CheckData.allfieldIsNotNUll(commoditySeckill)) {
			vo.setCommodityseckillId(commoditySeckill.getCommodityseckillId());
			vo.setCommodityseckillName(commoditySeckill.getCommodityseckillName());
			vo.setSeckillPrice(Format.keepTwoMoney(commoditySeckill.getSeckillPrice()));
			vo.setSeckillDiscountprice(Format.keepTwoMoney(commoditySeckill.getSeckillDiscountprice()));
			vo.setSeckillAmount(commoditySeckill.getSeckillAmount());
			vo.setSeckillAllowance(commoditySeckill.getSeckillAllowance());
			vo.setSeckillCommodityimg(SystemParam.DOMAIN_NAME+commoditySeckill.getSeckillCommodityimg());
			vo.setSeckillSpecification(commoditySeckill.getSeckillSpecification());
			vo.setSeckillRealSale(commoditySeckill.getSeckillRealSale());
			vo.setSeckillVirtualSales(commoditySeckill.getSeckillVirtualSales());
			vo.setSeckillOnsale(commoditySeckill.getSeckillOnsale());//是否出售
			vo.setSeckillState(commoditySeckill.getSeckillState());//是否开启秒杀
			vo.setSeckillClassify(commoditySeckill.getSeckillClassify());
			vo.setAddTime(commoditySeckill.getAddTime());
			vo.setStartTime(commoditySeckill.getStartTime());
			vo.setEndTime(commoditySeckill.getEndTime());
			vo.setSeckillDescription(commoditySeckill.getSeckillDescription());
			Date nowDate= new Date();
			long startInterval =nowDate.getTime() - vo.getStartTime().getTime();//距离开始时间
			long endInterval =vo.getEndTime().getTime() -nowDate.getTime() ;//距离结束时间
			vo.setEndMseconds(endInterval);
			vo.setStartMseconds(startInterval);
		}
		return vo;
	}

	@Override
	public List<CommoditySeckillListInfo> selectCommoditySeckillList() {
		List <CommoditySeckillListInfo> commodityVOList =new ArrayList<CommoditySeckillListInfo>();
		//封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
		List<CommoditySeckill> commodityList=commoditySeckillDao.selectCommoditySeckillList(map);
		for (int i = 0; i < commodityList.size(); i++) {
			CommoditySeckillListInfo vo=new CommoditySeckillListInfo();
			vo.setCommodityseckillId(commodityList.get(i).getCommodityseckillId());
			vo.setCommodityseckillName(commodityList.get(i).getCommodityseckillName());
			vo.setSeckillPrice(Format.keepTwoMoney(commodityList.get(i).getSeckillPrice()));
			vo.setSeckillDiscountprice(Format.keepTwoMoney(commodityList.get(i).getSeckillDiscountprice()));
		
			if (CheckData.isNotNullOrEmpty(commodityList.get(i).getSeckillCommodityimg())) {
				vo.setSeckillCommodityimg(SystemParam.DOMAIN_NAME+commodityList.get(i).getSeckillCommodityimg());
			}
			commodityVOList.add(vo);
		}
		return commodityVOList;
	}

	@Override
	public List<CommoditySeckill> selectAllCommoditySeckill() {
		
		return commoditySeckillDao.selectAllCommoditySeckill();
	}
	
	

}
