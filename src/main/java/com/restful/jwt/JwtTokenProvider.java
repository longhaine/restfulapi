package com.restful.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	private final String secretKey = "youluckybastard";
	private final long expiration = 6000000L;
	
	public String generateToken(String email) {
		Date now = new Date();
		
		Date expiryDate = new Date(now.getTime() + expiration);
		return Jwts.builder().setSubject(email)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	public String getAccountEmailFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	public boolean validateToken(String token) {
		try {
		Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		return true;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return false;
	}
}
