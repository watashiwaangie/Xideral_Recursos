package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Product;

public interface ProductDAO {

	public List<Product> getProducts();

	public void saveProduct(Product myProduct);

	public Product getProduct(int myId);

	public void deleteProduct(int myId);
	
}
