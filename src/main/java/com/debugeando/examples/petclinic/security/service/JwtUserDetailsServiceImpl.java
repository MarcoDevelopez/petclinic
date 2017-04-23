package com.debugeando.examples.petclinic.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.debugeando.examples.petclinic.model.User;
import com.debugeando.examples.petclinic.security.JwtUserFactory;
import com.debugeando.examples.petclinic.service.ClinicService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClinicService clinicService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = clinicService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'", username));
		} else {
			return JwtUserFactory.create(user);
		}
	}

}
