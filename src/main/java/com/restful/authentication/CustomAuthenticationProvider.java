package com.restful.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.restful.entity.Account;
import com.restful.service.AccountService;




@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	AccountService accountService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("Authentication here");
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		Account account = null;
		// if authority empty == false, this authentication came from jwtAuthenticationFilter
		// if authority empty == true, this authentication came from login api
//		if(authentication.getAuthorities().isEmpty()) {
			account = accountService.login(email, password);
//		}
		if(account == null) {
			throw new BadCredentialsException("WRONG!!!");
		}
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(String.valueOf(account.getRole())));
		return new UsernamePasswordAuthenticationToken(email,password,authority);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
