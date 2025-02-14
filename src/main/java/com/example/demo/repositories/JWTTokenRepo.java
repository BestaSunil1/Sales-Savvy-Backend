package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.JWTToken;

import jakarta.transaction.Transactional;
@Repository
public interface JWTTokenRepo extends JpaRepository<JWTToken, Integer>{
	@Query("SELECT t FROM JWTToken t WHERE t.user.user_id = :user_id")
	JWTToken findByUserId(int user_id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM JWTToken t WHERE t.user.user_id = :user_id")
	void deleteByUserId(int user_id);
}
