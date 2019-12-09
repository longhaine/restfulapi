package com.restful.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.entity.Product;
import com.restful.service.ProductService;

@Controller
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/popular")
	public ArrayList<Product> getPopularProduct() {
		return productService.findPopularProduct();
	}
	@GetMapping(value="/all")
	public Iterable<Product> getAll(){
		return productService.findAll();
	}
}
