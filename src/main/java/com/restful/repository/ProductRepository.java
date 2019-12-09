package com.restful.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restful.entity.Category;
import com.restful.entity.Product;
import java.lang.String;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	@Query(value ="SELECT product.id, product.name, product.price, product.image, product.gender,product.id_brands,product.id_categories FROM product limit 5",nativeQuery = true)
	ArrayList<Product> findPopularProduct();
	ArrayList<Product> findByCategoryAndGender(Category category,String gender);
	ArrayList<Product> findByGender(String gender);
	Optional<Product> findById(Integer productId);
	@Query(value ="SELECT product.* FROM product inner join category ON product.id_categories = category.id where MATCH (product.name) AGAINST (:name) and category.name = :categoryName",nativeQuery = true)
	ArrayList<Product> findByNameAndCategory(@Param("name")String name,@Param("categoryName")String categoryName);
	@Query(value ="SELECT product.* FROM product where MATCH (product.name) AGAINST (:name)",nativeQuery = true)
	ArrayList<Product> findByName(@Param("name")String name);
}
