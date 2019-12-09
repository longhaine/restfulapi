package com.restful.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restful.entity.Account;
import java.lang.String;
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
}
