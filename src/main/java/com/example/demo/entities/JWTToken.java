package com.example.demo.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jwt_tokens")
public class JWTToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int token_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@Column
	private String token;
	@Column(name="expires_at")
	private LocalDateTime expries_at;
	
	
	public JWTToken() {
		super();
	}
	public JWTToken(int toked_id, User user, String token, LocalDateTime expries_at) {
		super();
		this.token_id = toked_id;
		this.user = user;
		this.token = token;
		this.expries_at = expries_at;
	}
	public JWTToken(User user, String token, LocalDateTime expries_at) {
		super();
		this.user = user;
		this.token = token;
		this.expries_at = expries_at;
	}
	public int getToked_id() {
		return token_id;
	}
	public void setToked_id(int toked_id) {
		this.token_id = toked_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getExpriesAt() {
		return expries_at;
	}
	public void setExpriesAt(LocalDateTime expries_at) {
		this.expries_at = expries_at;
	}
	
}
