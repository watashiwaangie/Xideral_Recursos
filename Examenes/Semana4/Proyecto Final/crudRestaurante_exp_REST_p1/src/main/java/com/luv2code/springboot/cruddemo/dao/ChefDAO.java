package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import com.luv2code.springboot.cruddemo.entity.Chef;

public interface ChefDAO {

	List<Chef> findAll();
	
	Chef findById(int theId);
	
	void save(Chef theChef);
	
	void deleteById(int theId);
	
}
