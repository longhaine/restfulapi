package com.restful.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.entity.Account;
import com.restful.jwt.JwtTokenProvider;


@Controller
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@PostMapping("/doLogin")
	public ResponseTokenLogin doLogin(@RequestBody Account account) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getEmail(),account.getPassword()));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication.getName());
			ResponseTokenLogin response = new ResponseTokenLogin(token, "success"); 
			return response;
		}
		catch(BadCredentialsException ex) {
			return new ResponseTokenLogin("null", "failed");
		}
	}
	@GetMapping("/getEmailAccountFromToken")
	public String getAccountFromToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		System.out.println(token);
		if(token != null) {
			return tokenProvider.getAccountEmailFromJWT(token);
		}
		return null;
	}
	class ResponseTokenLogin{
		private String token;
		private String message;
		public String getToken() {
			return token;
		}
		public String getMessage() {
			return message;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		ResponseTokenLogin(String token,String message) {
			this.token = token;
			this.message = message;
		}
	}
}
