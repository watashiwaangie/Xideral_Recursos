package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.ProductDAO;
import com.springdemo.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	// need to inject customer dao
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public void saveProduct(Product myProduct) {

		productDAO.saveProduct(myProduct);
	}

	@Override
	@Transactional
	public Product getProduct(int myId) {
		
		return productDAO.getProduct(myId);
	}

	@Override
	@Transactional
	public void deleteProduct(int myId) {
		
		productDAO.deleteProduct(myId);
	}
}





