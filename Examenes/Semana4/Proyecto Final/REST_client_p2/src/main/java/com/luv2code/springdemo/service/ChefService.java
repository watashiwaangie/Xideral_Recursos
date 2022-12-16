package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.model.Chef;

public interface ChefService {

	public List<Chef> getChefs();

	public void saveChef(Chef theChef);

	public Chef getChef(int theId);

	public void deleteChef(int theId);

	List<Chef> getChef();
	
}
