package com.menglin.invest.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menglin.invest.dao.ProductDao;
import com.menglin.invest.entity.Product;
import com.menglin.invest.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	
	@Autowired
	private ProductDao productDao;
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProduct(Product book) {
		// TODO Auto-generated method stub
		
	}

}
