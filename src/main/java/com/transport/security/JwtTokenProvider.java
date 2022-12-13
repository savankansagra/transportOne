package com.transport.security;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.transport.entities.UserRoles;
import com.transport.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String SECREAT_KEY = "returnTrip,network";
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMiliSeconds = 3600000*100; // 1*100 hour
	
	@Autowired
	private MyUserDetails myUserDetails;

	//create token with details.
	public String createTokenWithTelephoneNumberAndRole(String telephoneNumber, List<UserRoles> appUserRoles) {
		//put key as telephoneNumber
		Claims claims = Jwts.claims().setSubject(telephoneNumber);
		//set the roles in claims.
		claims.put("auth", appUserRoles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
		
		Date now = new Date();
		Date Validity = new Date(now.getTime()+ validityInMiliSeconds);
		
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(Validity)
				.signWith(SignatureAlgorithm.HS256, SECREAT_KEY)
				.compact();
	}

	//get the token from the request.
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if( bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	// check for token validity.
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECREAT_KEY).parseClaimsJws(token);
			return true;
		} catch( JwtException | IllegalArgumentException e ) {
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Authenticate the User Request.
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(getTelephoneNumberFromToken(token));
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
	}
	
	public String getTelephoneNumberFromToken(String token) {
		return Jwts.parser().setSigningKey(SECREAT_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	
	
}
