package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.cruddemo.entity.Chef;
import com.luv2code.springboot.cruddemo.service.ChefService;

@Controller
@RequestMapping("/mvc")
public class ChefMvcController {

	// need to inject our customer service
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/chefs")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Chef> theCustomers = chefService.findAll();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Chef theCustomer = new Chef();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Chef theCustomer) {
		
		// save the customer using our service
		chefService.save(theCustomer);	
		
		return "redirect:/mvc/chefs";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Chef theCustomer = chefService.findById(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		chefService.deleteById(theId);
		
		return "redirect:/mvc/chefs";
	}

}










