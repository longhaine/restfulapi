package com.restful.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restful.entity.Order_Account;
import java.lang.String;

@Repository
public interface Order_AccountRepository extends CrudRepository<Order_Account, Integer> {
	ArrayList<Order_Account> findByIdsessionOrderByIdDesc(String sessionid);
	ArrayList<Order_Account> findByEmailOrderByIdDesc(String email);
	Optional<Order_Account>findByIdAndEmail(Integer id, String email);
	Optional<Order_Account> findByIdAndIdsession(Integer id, String idSession);
	ArrayList<Order_Account> findAll();
}
