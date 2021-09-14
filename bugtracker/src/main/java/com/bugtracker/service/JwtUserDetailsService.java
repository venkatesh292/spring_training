package com.bugtracker.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if ("venkatesh".equals(username)) {
			
			return new User(username, "$2y$12$ZGlBLMUQ9lV2/YXZ7F3pie8GsSZO3zrgFU3r7qBAF4bZTKFFFnfn6",
					new ArrayList<>());
		}else {
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	
	}
}
