package com.restful.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restful.entity.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {
	
}
