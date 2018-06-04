package com.menglin.triproapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.AdressDao;
import com.menglin.triproapp.dao.CommodityDao;
import com.menglin.triproapp.dao.UserDao;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.CommodityListInfo;

/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:48:12 
 */
@Service("commodityService")
public class CommodityService implements ICommodityService {
	
	@Resource
	private  CommodityDao commodityDao;
	
	@Resource  
    private UserDao userDao;
	
	
	@Override
	public void deleteByPrimaryKey(Integer id) {
		
		commodityDao.deleteByPrimaryKey(id);

	}

	@Override
	public void save(Commodity record) {
		commodityDao.insertSelective(record);

	}

	@Override
	public Commodity get(Integer id) {
		
		return commodityDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Commodity record) {
		commodityDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public void soldOutCommodity(Integer[] commodityIds) {
		commodityDao.soldOutByIds(commodityIds);
		
	}
	
	/**
	 * 商品详情，并匹配给 CommodityDetailVO 实体
	 */
	@Override
	public CommodityDetailVO selectCommodityDetail( Integer commodityId) {
		Commodity commodity=commodityDao.selectByPrimaryKey(commodityId);
		CommodityDetailVO vo=new CommodityDetailVO();
		if (CheckData.isNotNullOrEmpty(commodity)) {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vo.setCommodityId(commodity.getCommodityid());
			vo.setCommodityName(commodity.getCommodityName());
			vo.setPrice(Format.keepTwoMoney(commodity.getPrice()));
			vo.setDiscountPrice(Format.keepTwoMoney(commodity.getDiscountPrice()));
			vo.setAmount(commodity.getAmount());
			vo.setAllowance(commodity.getAllowance());
			vo.setCommodityImg(SystemParam.DOMAIN_NAME+commodity.getCommodityImg());
			vo.setSpecification(commodity.getSpecification());
			vo.setRealSale(commodity.getRealSale());
			vo.setVirtualSales(commodity.getVirtualSales());
			vo.setState(commodity.getState());
			vo.setClassify(commodity.getClassify());
			vo.setAddTime(sdf.format(commodity.getAddTime()));
			vo.setUpdateTime(sdf.format(commodity.getUpdateTime()));
			vo.setDescription(commodity.getDescription());
		}
		return vo;
	}
	/**
	 * 查询所有商品，并匹配给 CommodityDetailVO 实体
	 */
	@Override
	public List<CommodityListInfo> selectCommodityList( Integer condition,Integer classify,String commodityName) {
		List <CommodityListInfo> commodityVOList =new ArrayList<CommodityListInfo>();
		//封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("condition",condition);
        map.put("classify", classify);
        map.put("commodityName", commodityName);
		List<Commodity> commodityList=commodityDao.selectCommodityList(map);
		for (int i = 0; i < commodityList.size(); i++) {
			CommodityListInfo vo=new CommodityListInfo();
			vo.setCommodityId(commodityList.get(i).getCommodityid());
			vo.setCommodityName(commodityList.get(i).getCommodityName());
			vo.setPrice(Format.keepTwoMoney(commodityList.get(i).getPrice()));
			vo.setDiscountPrice(Format.keepTwoMoney(commodityList.get(i).getDiscountPrice()));
			if (CheckData.isNotNullOrEmpty(commodityList.get(i).getCommodityImg())) {
				vo.setCommodityImg(SystemParam.DOMAIN_NAME+commodityList.get(i).getCommodityImg());
			}
			commodityVOList.add(vo);
		}
		return commodityVOList;
	}

	@Override
	public PageBean<CommodityDetailVO> findByPage(Integer currentPage, Integer pageSize, Commodity model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("userPhone", model.getUserPhone());
        //总记录数
        int totalCount = commodityDao.selectCount(map1);
        
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
       
        List<Commodity> lists = commodityDao.findByPage(map);
        
        List<CommodityDetailVO> cDetailVOs=new ArrayList<CommodityDetailVO>();
        if (null!=lists) {
        	for (int i = 0; i < lists.size(); i++) {
        		CommodityDetailVO vo= new CommodityDetailVO();
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			vo.setCommodityId(lists.get(i).getCommodityid());
    			vo.setCommodityName(lists.get(i).getCommodityName());
    			vo.setPrice(Format.keepTwoMoney(lists.get(i).getPrice()));
    			vo.setDiscountPrice(Format.keepTwoMoney(lists.get(i).getDiscountPrice()));
    			vo.setAmount(lists.get(i).getAmount());
    			vo.setAllowance(lists.get(i).getAllowance());
    			vo.setCommodityImg(SystemParam.DOMAIN_NAME+lists.get(i).getCommodityImg());
    			vo.setSpecification(lists.get(i).getSpecification());
    			vo.setRealSale(lists.get(i).getRealSale());
    			vo.setVirtualSales(lists.get(i).getVirtualSales());
    			vo.setState(lists.get(i).getState());
    			vo.setClassify(lists.get(i).getClassify());
    			vo.setAddTime(sdf.format(lists.get(i).getAddTime()));
    			vo.setUpdateTime(sdf.format(lists.get(i).getUpdateTime()));
    			vo.setDescription(lists.get(i).getDescription());
    			cDetailVOs.add(vo);
			}
        	
		}
        
        PageBean<CommodityDetailVO> pageBean = new PageBean<CommodityDetailVO>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(cDetailVOs);

        return pageBean;
	}

}
