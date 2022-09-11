package com.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.transport.entities.User;
import com.transport.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepository.findUserByTelephoneNumber(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("user with telephonenumber '"+username+"' not found");
		}
		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.authorities(user.getAppUserRoles())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
		
	}

}
