package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public User userRegistration(User user) throws Exception{
			if(userRepository.findByUsername(user.getUsername()).isPresent()) {
				throw new RuntimeException("User Name already Exixts");
			}
			if(userRepository.findByEmail(user.getEmail()).isPresent()) {
				throw new RuntimeException("User email is already present");
			}
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			String ppwd = user.getPassword();
			String epwd = passwordEncoder.encode(ppwd);
			user.setPassword(epwd);
		return userRepository.save(user);
	}
}
