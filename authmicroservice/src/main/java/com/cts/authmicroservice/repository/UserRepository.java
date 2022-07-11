package com.cts.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.authmicroservice.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{
	
	public UserModel findByEmpUsername(String username);
}

