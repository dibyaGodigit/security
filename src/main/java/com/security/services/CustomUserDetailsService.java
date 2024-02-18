package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.models.CustomUserDetails;
import com.security.models.User;
import com.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository urepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User findByUsername = urepo.findByUsername(username);
	if(findByUsername == null) throw new UsernameNotFoundException("Please Signup");
		return  new CustomUserDetails(findByUsername);
	}

}
