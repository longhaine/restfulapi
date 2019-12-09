package com.restful.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restful.entity.Forgot;
import com.restful.entity.Account;
import java.util.Optional;

@Repository
public interface ForgotRepository extends CrudRepository<Forgot, String> {
	@Modifying
	@Query(value = "INSERT INTO `forgot`(`email`, `pathinfo`,`date`) VALUES (:email,MD5(:pathInfo),:date)",nativeQuery = true)
	void save(@Param("email")String email,@Param("pathInfo")String pathInfo,@Param("date")String date);
	Optional<Forgot> findByAccount(Account account);
}
