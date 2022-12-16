package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Chef;

public interface ChefService {

	public List<Chef> findAll();
	
	public Chef findById(int theId);
	
	public void save(Chef theChef);
	
	public void deleteById(int theId);
	
}
