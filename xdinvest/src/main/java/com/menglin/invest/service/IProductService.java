package com.menglin.invest.service;


import com.menglin.invest.entity.Product;
import com.menglin.invest.util.PageBean;

public interface IProductService {
	
    
    public Product get(int productId);
   	public void save(Product product);
   	public void delete(int productId);
   	public void update(Product product);
   	PageBean<Product> findByPage(Integer currentPage,Integer pageSize,Product product,String startTime,String endTime);
   	
}
