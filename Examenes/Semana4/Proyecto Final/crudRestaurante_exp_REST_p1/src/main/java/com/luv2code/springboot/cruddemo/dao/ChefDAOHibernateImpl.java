package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Chef;

@Repository
public class ChefDAOHibernateImpl implements ChefDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public ChefDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Chef> findAll() {
		System.out.println("ChefDAOHibernateImpl");
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Chef> theQuery =
				currentSession.createQuery("from Chef", Chef.class);
		
		// execute query and get result list
		List<Chef> chefs = theQuery.getResultList();
		
		// return the results		
		return chefs;
	}


	@Override
	public Chef findById(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Chef theChef =
				currentSession.get(Chef.class, theId);
		
		// return the employee
		return theChef;
	}


	@Override
	public void save(Chef theChef) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theChef);
	}


	@Override
	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery(
						"delete from Chef where id=:chefId");
		
		theQuery.setParameter("chefId", theId);
		
		theQuery.executeUpdate();
	}

}







