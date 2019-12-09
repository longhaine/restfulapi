package com.restful.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.entity.Order_Account;
import com.restful.repository.Order_AccountRepository;

@Service
public class Order_AccountService {
	
	@Autowired
	private Order_AccountRepository orderRepository;
	
	public ArrayList<Order_Account> findByIdsessionOrderByIdDesc(String sessionid)
	{
		return orderRepository.findByIdsessionOrderByIdDesc(sessionid);
	}
	public ArrayList<Order_Account> findByEmailOrderByIdDesc(String email)
	{
		return orderRepository.findByEmailOrderByIdDesc(email);
	}
	public Optional<Order_Account> findByIdAndEmail(Integer id, String email){
		return orderRepository.findByIdAndEmail(id, email);
	}
	public Optional<Order_Account> findByIdandIdsession(Integer id, String idSession){
		return orderRepository.findByIdAndIdsession(id, idSession);
	}
	public Order_Account save(Order_Account order){
		return orderRepository.save(order);
	}
	public ArrayList<Order_Account> getAll(){
		return orderRepository.findAll();
	}
}
