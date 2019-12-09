package com.restful.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.entity.Category;
import com.restful.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ArrayList<Category> findAll(){
		ArrayList<Category> list = new ArrayList<Category>();
		for(Category category : categoryRepository.findAll())
		{
			list.add(category);
		}
		return list;
	}
	
	public List<Category> findByGender(String gender) {
		return categoryRepository.findByGender(gender);
	}
	public ArrayList<Category> findByNameAndGender(String name,String gender){
		return categoryRepository.findByNameAndGender(name, gender);
	}
	public Optional<Category> findByName(String name) {
		return categoryRepository.findByName(name);
	}
}
