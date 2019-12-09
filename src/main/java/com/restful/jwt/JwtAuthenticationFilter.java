package com.restful.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.restful.entity.Account;
import com.restful.service.AccountService;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	AccountService accountService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
		try {
			String token = request.getHeader("Authorization").replace("Bearer ", "");
			System.out.println("Token: "+token);
			if(token != null && tokenProvider.validateToken(token)) {
				String email = tokenProvider.getAccountEmailFromJWT(token);
				Optional<Account> account = accountService.findById(email);
				if(account.isPresent()) {
					List<GrantedAuthority> authority = new ArrayList<>();
					authority.add(new SimpleGrantedAuthority(String.valueOf(account.get().getRole())));
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account.get().getEmail(),account.get().getPassword(),authority);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		catch(Exception ex){
			System.out.println("THERE IS NO TOKEN");
		}
		filterChain.doFilter(request, response);
	}
}
