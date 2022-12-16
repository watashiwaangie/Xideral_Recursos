package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Chef;
import com.luv2code.springboot.cruddemo.service.ChefService;

@RestController
@RequestMapping("/rest")
public class ChefRestController {

	private ChefService chefService;
	
	@Autowired
	public ChefRestController(ChefService theChefService) {
		chefService = theChefService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/chefs")
	public List<Chef> findAll() {
		return chefService.findAll();
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/chefs/{chefId}")
	public Chef getChef(@PathVariable int chefId) throws Exception {
		
		Chef theChef = chefService.findById(chefId);
		
		if (theChef == null) {
			throw new Exception("Chef id not found - " + chefId);
		}
		
		return theChef;
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/chefs")
	public Chef addChef(@RequestBody Chef theChef) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theChef.setId(0);
		
		chefService.save(theChef);
		
		return theChef;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/chefs")
	public Chef updateChef(@RequestBody Chef theChef) {
		
		chefService.save(theChef);
		
		return theChef;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/chefs/{chefId}")
	public String deleteChef(@PathVariable int chefId) {
		
		Chef tempChef = chefService.findById(chefId);
		
		// throw exception if null
		
		if (tempChef == null) {
			throw new RuntimeException("Chef id not found - " + chefId);
		}
		
		chefService.deleteById(chefId);
		
		return "Deleted chef id - " + chefId;
	}
	
}










