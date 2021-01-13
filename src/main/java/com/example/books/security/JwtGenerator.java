package com.example.books.security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.example.books.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@Transactional
public class JwtGenerator {

	private static final int JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(User user) {
		Map<String, Object> claims = setClaims(user);
		return doGenerateToken(claims, user.getUsername());
	}

	private Map<String, Object> setClaims(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", user.getUsername());
		return claims;
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}
}
