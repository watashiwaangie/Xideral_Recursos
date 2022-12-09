package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Product> getProducts() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Product> myQuery = 
				currentSession.createQuery("from Product order by id",
											Product.class);
		
		System.out.println("BBB");
		// execute query and get result list
		System.out.println(myQuery.getFirstResult());
		List<Product> products = myQuery.getResultList();
		System.out.println("CCC");
		
				
		// return the results		
		return products;
	}

	@Override
	public void saveProduct(Product myProduct) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the 
		currentSession.saveOrUpdate(myProduct);
		
	}

	@Override
	public Product getProduct(int myId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Product myProduct = currentSession.get(Product.class, myId);
		
		return myProduct;
	}

	@Override
	public void deleteProduct(int myId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query myQuery = 
				currentSession.createQuery("delete from Product where id=:productId");
		myQuery.setParameter("productId", myId);
		
		myQuery.executeUpdate();		
	}

}











