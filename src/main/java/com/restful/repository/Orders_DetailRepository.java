package com.restful.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restful.entity.Orders_Detail;
import com.restful.entity.Order_Account;

import java.util.ArrayList;

@Repository
public interface Orders_DetailRepository extends CrudRepository<Orders_Detail, Integer>{
	ArrayList<Orders_Detail> findByOrder(Order_Account order);
}
