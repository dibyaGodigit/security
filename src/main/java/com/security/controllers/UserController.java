package com.security.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.models.User;
import com.security.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping("getAll")
	public ResponseEntity<List<User>> getAllUser(){
		return new ResponseEntity<List<User>>(service.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("get/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username){
		return new ResponseEntity<User>(service.getUser(username),HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> add(@RequestBody User user){
		service.add(user);
		return new ResponseEntity<String>("Added",HttpStatus.OK); 
	}
}
