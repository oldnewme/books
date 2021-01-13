package com.example.books.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;


@Component
@Transactional(propagation = Propagation.REQUIRED)
public class JwtValidator {

	@Value("${jwt.secret}")
	private String secret;

	public boolean isValid(String jwtToken) {
		return Jwts.parser().setSigningKey(secret).isSigned(jwtToken);
    }

    public String getTokenFromRequest(String request){
        return request.substring(7);
    }

    public String getUsernameFromToken(String jwtToken){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody().getSubject();
    }
    


}