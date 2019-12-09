package com.restful.service;
import java.util.Optional;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.entity.Account;
import com.restful.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Optional<Account> findById(String email) {
		return accountRepository.findById(email);
	}
	public Account login(String email, String password){
		Optional<Account> accountOptional = findById(email);
		if(accountOptional.isPresent())
		{
			if(BCrypt.checkpw(password, accountOptional.get().getPassword()))
			{
				return accountOptional.get();
			}
		}
		return null;
	}
	public Account authentication(String email, String hashPass){
		Optional<Account> account = findById(email);
		if(account.isPresent())
		{
			if(account.get().getPassword().equals(hashPass))
			{
				return account.get();
			}
		}
		return null;
	}
	public Account save(Account account) {
		return accountRepository.save(account);
	}
	public boolean register(Account account) {
		Optional<Account> accountOptional = findById(account.getEmail());
		if(!accountOptional.isPresent()) // if account doesn't exist
		{
			account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(6)));
			save(account);
			return true; // if it's true
		}
		return false;
	}
}
