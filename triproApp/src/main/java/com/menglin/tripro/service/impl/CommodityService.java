package com.menglin.tripro.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.tripro.dao.AdressDao;
import com.menglin.tripro.dao.CommodityDao;
import com.menglin.tripro.dao.UserDao;
import com.menglin.tripro.entity.Commodity;
import com.menglin.tripro.entity.User;
import com.menglin.tripro.service.ICommodityService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.Format;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.SystemParam;
import com.menglin.tripro.vo.CommodityDetailVO;

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
	 * 查询所有商品，并匹配给 CommodityDetailVO 实体
	 */
	@Override
	public List<CommodityDetailVO> selectCommodityList(Integer uid) {
		List <CommodityDetailVO> commodityVOList =new ArrayList<CommodityDetailVO>();
		List<Commodity> commodityList=commodityDao.selectCommodityList();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (CheckData.isNotEmpty(commodityList)) {
			if (null!=uid) {
				User user=userDao.selectByPrimaryKey(uid);
				if (CheckData.isNotNullOrEmpty(user) && user.getIdentity()==2 ) { //用户身份：1个人用户2供销商
					for (int i = 0; i < commodityList.size(); i++) {
						CommodityDetailVO vo=new CommodityDetailVO();
						vo.setCommodityId(commodityList.get(i).getId());
						vo.setName(commodityList.get(i).getName());
						vo.setPrice(Format.keepTwoMoney(commodityList.get(i).getPrice()));
						vo.setDiscountPrice(Format.keepTwoMoney(commodityList.get(i).getDiscountPrice()));
						vo.setAmount(commodityList.get(i).getAmount());
						vo.setAllowance(commodityList.get(i).getAllowance());
						vo.setImg(SystemParam.DOMAIN_NAME+commodityList.get(i).getImg());
						vo.setSpecification(commodityList.get(i).getSpecification());
						vo.setSales(commodityList.get(i).getSales());
						vo.setState(commodityList.get(i).getState());
						vo.setAddTime(sdf.format(commodityList.get(i).getAddTime()));
						vo.setUpdateTime(sdf.format(commodityList.get(i).getUpdateTime()));
						vo.setDescription(commodityList.get(i).getDescription());
						commodityVOList.add(vo);
					}
				}else{
					for (int i = 0; i < commodityList.size(); i++) {
						CommodityDetailVO vo=new CommodityDetailVO();
						vo.setCommodityId(commodityList.get(i).getId());
						vo.setName(commodityList.get(i).getName());
						vo.setPrice(Format.keepTwoMoney(commodityList.get(i).getPrice()));
						//无用户ID 或者未审核通过，就不显示供货价
	//					vo.setDiscountPrice(Format.keepTwoMoney(commodityList.get(i).getDiscountPrice())); 
						vo.setAmount(commodityList.get(i).getAmount());
						vo.setAllowance(commodityList.get(i).getAllowance());
						vo.setImg(SystemParam.DOMAIN_NAME+commodityList.get(i).getImg());
						vo.setSpecification(commodityList.get(i).getSpecification());
						vo.setSales(commodityList.get(i).getSales());
						vo.setState(commodityList.get(i).getState());
						vo.setAddTime(sdf.format(commodityList.get(i).getAddTime()));
						vo.setUpdateTime(sdf.format(commodityList.get(i).getUpdateTime()));
						vo.setDescription(commodityList.get(i).getDescription());
						commodityVOList.add(vo);
					}
				}
		  }else{
			  //uid 为null 
				for (int i = 0; i < commodityList.size(); i++) {
					CommodityDetailVO vo=new CommodityDetailVO();
					vo.setCommodityId(commodityList.get(i).getId());
					vo.setName(commodityList.get(i).getName());
					vo.setPrice(Format.keepTwoMoney(commodityList.get(i).getPrice()));
					//无用户ID 或者未审核通过，就不显示供货价
//					vo.setDiscountPrice(Format.keepTwoMoney(commodityList.get(i).getDiscountPrice())); 
					vo.setAmount(commodityList.get(i).getAmount());
					vo.setAllowance(commodityList.get(i).getAllowance());
					vo.setImg(SystemParam.DOMAIN_NAME+commodityList.get(i).getImg());
					vo.setSpecification(commodityList.get(i).getSpecification());
					vo.setSales(commodityList.get(i).getSales());
					vo.setState(commodityList.get(i).getState());
					vo.setAddTime(sdf.format(commodityList.get(i).getAddTime()));
					vo.setUpdateTime(sdf.format(commodityList.get(i).getUpdateTime()));
					vo.setDescription(commodityList.get(i).getDescription());
					commodityVOList.add(vo);
				}
		  }
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
    			vo.setCommodityId(lists.get(i).getId());
    			vo.setName(lists.get(i).getName());
    			vo.setPrice(Format.keepTwoMoney(lists.get(i).getPrice()));
    			vo.setDiscountPrice(Format.keepTwoMoney(lists.get(i).getDiscountPrice()));
    			vo.setAmount(lists.get(i).getAmount());
    			vo.setAllowance(lists.get(i).getAllowance());
    			vo.setImg(SystemParam.DOMAIN_NAME+lists.get(i).getImg());
    			vo.setSpecification(lists.get(i).getSpecification());
    			vo.setSales(lists.get(i).getSales());
    			vo.setState(lists.get(i).getState());
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
