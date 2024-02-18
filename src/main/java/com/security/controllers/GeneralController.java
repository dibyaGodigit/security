package com.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class GeneralController {
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("HELLO",HttpStatus.OK);
		
	}
	
}
