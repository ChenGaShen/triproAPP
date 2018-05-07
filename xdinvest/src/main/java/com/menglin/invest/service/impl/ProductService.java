package com.menglin.invest.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.ProductDao;
import com.menglin.invest.entity.Information;
import com.menglin.invest.entity.Product;
import com.menglin.invest.service.IProductService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.DateUntil;
import com.menglin.invest.util.PageBean;


@Service("productService")
public class ProductService implements IProductService {
	
	
	@Resource
	private ProductDao productDao;

	@Override
	public Product get(int productId) {
		
		return productDao.selectByPrimaryKey(productId);
	}

	@Override
	public void save(Product product) {
		productDao.insertSelective(product);
		
	}

	@Override
	public void delete(int productId) {
		productDao.deleteByPrimaryKey(productId);
		
	}

	@Override
	public void update(Product product) {
		productDao.updateByPrimaryKeySelective(product);
		
	}

	@Override
	public PageBean<Product> findByPage(Integer currentPage, Integer pageSize, Product product, String startTime,
			String endTime) {
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("productName", product.getProductName());
        map1.put("fundManager", product.getFundManager());
        map1.put("productState", product.getProductState());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = productDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	map.put("productName", product.getProductName());
        map.put("fundManager", product.getFundManager());
        map.put("productState", product.getProductState());
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<Product> lists = productDao.findByPage(map);
        
        PageBean<Product> pageBean = new PageBean<Product>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);
        return pageBean;
	}

	

}
