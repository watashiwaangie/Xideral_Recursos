package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Product;
import com.springdemo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	// need to inject our customer service
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String listProducts(Model theModel) {
		System.out.println("AAAA");
		// get customers from the service
		List<Product> myProducts = productService.getProducts();
		
		for (Product p: myProducts)
			System.out.println(p);
				
		// add the customers to the model
		theModel.addAttribute("products", myProducts);
		
		return "list-products";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Product myProduct = new Product();
		
		theModel.addAttribute("product", myProduct);
		
		return "product-form";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product myProduct) {
		
		// save the customer using our service
		productService.saveProduct(myProduct);	
		
		return "redirect:/product/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("productId") int myId,
									Model theModel) {
		
		// get the customer from our service
		Product myProduct = productService.getProduct(myId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("product", myProduct);
		
		// send over to our form		
		return "product-form";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("productId") int myId) {
		
		// delete the customer
		productService.deleteProduct(myId);
		
		return "redirect:/product/list";
	}
}










