package com.restful.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.entity.Brand;
import com.restful.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public ArrayList<Brand> findAll(){
		ArrayList<Brand> list = new ArrayList<Brand>();
		for(Brand brand : brandRepository.findAll())
		{
			list.add(brand);
		}
		return list;
	}
}
