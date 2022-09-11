package com.transport.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.transport.exception.CustomException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//final String authorizationHeader = request.getHeader("Authorization");
		String token = jwtTokenProvider.resolveToken(request);
		
		try {
			if(token != null && jwtTokenProvider.validateToken(token)) { //valdiate the token
				//get the username from token and check into database.
				//if it matches then create usernamepasswordauthentication obejct with status success.
				//if it doesnot match then throw the exception which will be capture in next catch block.
				Authentication auth = jwtTokenProvider.getAuthentication(token); 
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch ( CustomException ex) {
			SecurityContextHolder.clearContext();
			response.sendError(ex.getHttpStatus().value(), ex.getMessage());
			return;
		}
		
		
		//Call the next filter
		filterChain.doFilter(request, response);
	}

}
