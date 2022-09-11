package com.transport.entities;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
	
	ROLE_ADMIN,
	ROLE_USER,
	ROLE_DRIVER;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}
	
}
