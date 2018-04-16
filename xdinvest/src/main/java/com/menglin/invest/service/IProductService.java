package com.menglin.invest.service;

import java.util.List;

import com.menglin.invest.entity.Product;

public interface IProductService {
	public List<Product> findAll();
    public void saveProduct(Product book);
    public Product findOne(int id);
    public void delete(int id);
}
