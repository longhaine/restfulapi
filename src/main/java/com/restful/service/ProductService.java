package com.restful.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.entity.Category;
import com.restful.entity.Product;
import com.restful.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
    
	public Iterable<Product> findAll(){
		return productRepository.findAll();
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
	public ArrayList<Product> findPopularProduct(){
		return productRepository.findPopularProduct();
	}
	public ArrayList<Product> findByCategoryAndGender(Category category,String gender){
		return productRepository.findByCategoryAndGender(category,gender);
	}
	public ArrayList<Product> findByGender(String gender){
		return productRepository.findByGender(gender);
	}
	
	public Optional<Product> findById(Integer productId) {
		return productRepository.findById(productId);
	}
	public ArrayList<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	public ArrayList<Product> findByNameAndCategory(String name, String categoryName){
		return productRepository.findByNameAndCategory(name, categoryName);
	}
}
