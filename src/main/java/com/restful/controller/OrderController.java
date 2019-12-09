package com.restful.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.entity.Order_Account;
import com.restful.service.Order_AccountService;

@Controller
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private Order_AccountService orderAccountService;
	
	@GetMapping("/all")
	public ArrayList<Order_Account> getAllOrderAccount(){
		return orderAccountService.getAll();
	}
}
