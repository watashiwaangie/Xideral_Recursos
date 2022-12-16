package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.model.Chef;
import com.luv2code.springdemo.service.ChefService;

@Controller
@RequestMapping("/chef")
public class ChefController {

	// need to inject our customer service
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/list")
	public String listChefs(Model theModel) {
		
		// get customers from the service
		List<Chef> thechefs = chefService.getChefs();
				
		// add the customers to the model
		theModel.addAttribute("chefs", thechefs);
		
		return "list-chefs";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Chef theChef = new Chef();
		
		theModel.addAttribute("chef", theChef);
		
		return "chef-form";
	}
	
	@PostMapping("/saveChef")
	public String saveChef(@ModelAttribute("chef") Chef theChef) {
		
		// save the chef using our service
		chefService.saveChef(theChef);	
		
		return "redirect:/chef/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("chefId") int theId,
									Model theModel) {
		
		// get the chef from our service
		Chef theChef = chefService.getChef(theId);	
		
		// set chef as a model attribute to pre-populate the form
		theModel.addAttribute("chef", theChef);
		
		// send over to our form		
		return "chef-form";
	}
	
	@GetMapping("/delete")
	public String deleteChef(@RequestParam("chefId") int theId) {
		
		// delete the customer
		chefService.deleteChef(theId);
		
		return "redirect:/chef/list";
	}
}










