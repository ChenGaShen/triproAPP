package com.menglin.invest.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.invest.dao.ProductDao;
import com.menglin.invest.entity.Product;
import com.menglin.invest.service.IProductService;
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
		
		return null;
	}

	

}
