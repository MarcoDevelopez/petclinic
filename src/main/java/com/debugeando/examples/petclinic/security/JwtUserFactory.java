package com.debugeando.examples.petclinic.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.debugeando.examples.petclinic.model.Authority;
import com.debugeando.examples.petclinic.model.User;

public final class JwtUserFactory {
	
	public JwtUserFactory() {
	}
	
	public static JwtUser create(User user) {
		return new JwtUser(
				user.getId(), 
				user.getUsername(), 
				user.getPassword(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				mapToGrantedAuthorities(user.getAuthorities()), 
				user.getEnabled(), 
				user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (Authority auth : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getName().name()));
		}
		return grantedAuthorities;
	}
}
