package com.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.models.User;
import com.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserService() {
//		list.add(new User("Dibya","1234","kumar@12"));
//		list.add(new User("Dibya12","12345","kumar@123"));
//		list.add(new User("Dibya56","12346","kumar@124"));
//		list.add(new User("Dibya78","12347","kumar@125"));
	}
	
	
	public List<User> getAllUser(){
		return urepo.findAll();
	}
	
	public User getUser(String uid) {
		return urepo.findByUsername(uid);
	}
	
	public void add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		urepo.save(user);
	}
	
}
