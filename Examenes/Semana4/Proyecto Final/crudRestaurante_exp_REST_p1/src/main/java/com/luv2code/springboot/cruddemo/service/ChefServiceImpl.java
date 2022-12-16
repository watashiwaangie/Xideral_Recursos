package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.ChefDAO;
import com.luv2code.springboot.cruddemo.entity.Chef;

@Service
public class ChefServiceImpl implements ChefService {

	
	private ChefDAO chefDAO;
	
	@Autowired
	public ChefServiceImpl(@Qualifier("chefDAOJdbcImpl") ChefDAO theChefDAO) {
		chefDAO = theChefDAO;
	}
	
	@Override
	@Transactional
	public List<Chef> findAll() {
		return chefDAO.findAll();
	}

	@Override
	@Transactional
	public Chef findById(int theId) {
		return chefDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Chef theChef) {
		chefDAO.save(theChef);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		chefDAO.deleteById(theId);
	}

}






