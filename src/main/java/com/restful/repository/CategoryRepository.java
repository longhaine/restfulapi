package com.restful.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restful.entity.Category;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	@Query(value ="SELECT category.id, category.name, category.gender FROM category  WHERE name = :name AND gender IN(:gender,'both')",nativeQuery = true)
	ArrayList<Category> findByNameAndGender(@Param("name")String name,@Param("gender")String gender);
	@Query(value ="SELECT id, name, gender FROM category where gender IN(:gender,'both')",nativeQuery = true)
	ArrayList<Category> findByGender(@Param("gender")String gender);
	Optional<Category> findByName(String name);
}
