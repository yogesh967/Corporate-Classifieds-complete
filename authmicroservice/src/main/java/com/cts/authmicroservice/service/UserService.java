package com.cts.authmicroservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;

public interface UserService extends UserDetailsService {

	UserToken login(UserModel userModel);

	AuthResponse getValidity(String token);

	public int getEmpId(String username);
}
