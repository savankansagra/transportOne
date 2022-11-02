package com.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/api/register").permitAll()
//			.antMatchers("/api/authentication/useremail").permitAll()
//			.antMatchers("/api/authentication/userTelephoneNumber").permitAll()
//			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//		
		// Disable CRFS ( Cross site request forgery
		http.csrf().disable();
		
		// No Session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Entry Points
		http.authorizeRequests()
			.antMatchers("/api/signup").permitAll()
			.antMatchers("/api/authentication/*").permitAll()
			.antMatchers("/api/signin/useremail").permitAll()
			.antMatchers("/api/authentication/useremail").permitAll()
			.antMatchers("/api/signin/usertelephonenumber").permitAll()
			.antMatchers("/api/authentication/usertelephonenumber").permitAll()
			.anyRequest().authenticated();
		
		// If a user try to access a resources without having enough permission
		// http.exceptionHandling().accessDeniedPage("/login");
		
		// Add the filter be
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			
		
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow swagger to be accessed without authentication
		web.ignoring()
			.antMatchers("/v2/api-docs")
			.antMatchers("/swagger-resources/**")
			.antMatchers("/swagger-ui.html")
			.antMatchers("/configuration/**")
			.antMatchers("/webjars/")
			.antMatchers("/public");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO - We are not using password. so used no password encoding.
		// but if password entered letter then use encoding and stored in database as encoded format. 
		return NoOpPasswordEncoder.getInstance();
	}
	
	// Getting the Authentication manager bean to load user from our database.
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
