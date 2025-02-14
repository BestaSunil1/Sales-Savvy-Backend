package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class RegistrationController {
	UserService userService;
	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping("/register")
	public ResponseEntity<?> registrationUser(@RequestBody User user) {
		try {
			User registeredUser = userService.userRegistration(user);
			return ResponseEntity.ok(Map.of("message", "userRegisteredSuccessfully", "user", registeredUser));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
		
	}
}
