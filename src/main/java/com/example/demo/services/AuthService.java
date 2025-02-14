package com.example.demo.services;



import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.JWTTokenRepo;
import com.example.demo.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {
	private final Key SIGINING_KEY;
	private  final UserRepository userRepository;
	private final JWTTokenRepo jwtTokenRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public AuthService(UserRepository userRepository, JWTTokenRepo jwtTokenRepo, @Value("${jwt.secret}") String jwtSecret) {
		this.userRepository = userRepository;
		this.jwtTokenRepo = jwtTokenRepo;
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.SIGINING_KEY = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
	
	public User autheticate(String username, String password) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		} return  user;
	}
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("role", user.getRole().name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ 3600000))
				.signWith(SIGINING_KEY, SignatureAlgorithm.HS512)
				.compact();
	}
}
