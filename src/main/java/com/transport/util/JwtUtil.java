package com.transport.util;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private String SECREAT_KEY = "returnTrip,network";
	
//	public String extractTelephoneNumber(String token) {
//		return extractClaim(token, Claims::getTelephoneNumber);
//		
//		
//	}
	
//	public String extractRole(String token) {
//		return extractClaim(token, Claims::getRole);
//	}
	
	
	
	public String createTokenWithTelephoneNumberAndRole(Map<String, Object> claims, String telephoneNumber, String ROLE) {
		return Jwts.builder().setClaims(claims)
				.claim("telephoneNumber", telephoneNumber)
				.claim("ROLE", ROLE)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, SECREAT_KEY)
				.compact();
	}

	public String extractTelephoneNumber(String jwtToken) {
		return extractClaim(jwtToken, Claims::getTelephoneNumber);
	}
	
	public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parser().setSigningKey(SECREAT_KEY).parseClaimsJws(jwtToken).getBody();
	}

	
	
}
