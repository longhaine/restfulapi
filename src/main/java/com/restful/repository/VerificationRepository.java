package com.restful.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restful.entity.Verification;
import java.lang.String;
import com.restful.entity.Account;

@Repository
public interface VerificationRepository extends CrudRepository<Verification, String>{
	@Modifying
	@Query(value = "INSERT INTO `verification`(`email`, `pathinfo`,`date`) VALUES (:email,MD5(:pathInfo),:date)",nativeQuery = true)
	void save(@Param("email")String email,@Param("pathInfo")String pathInfo,@Param("date")String date);
	Verification findByAccount(Account account);
}
