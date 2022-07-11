package com.cts.authmicroservice.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.authmicroservice.exception.UnauthorizedException;
import com.cts.authmicroservice.jwt.JwtUtil;
import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String userName)  {
		// all actions to check the correctness of the userID
		LOGGER.info("Inside loadbyusername");
		UserModel user = userRepository.findById(userName).orElse(null);
			return new User(user.getEmpUsername(), user.getEmpPassword(), new ArrayList<>());
	}

	public UserToken login(UserModel userModel)  {
		// validate password & return token
		final UserDetails userdetails = loadUserByUsername(userModel.getEmpUsername());
		UserToken userToken = new UserToken();
		if (userdetails.getPassword().equals(userModel.getEmpPassword())) {
			LOGGER.info("Inside login");
			userToken.setUsername(userModel.getEmpUsername());
			userToken.setEmpid(userRepository.findByEmpUsername(userModel.getEmpUsername()).getEmpid());
			userToken.setAuthToken(jwtUtil.generateToken(userdetails));
			return userToken;
		} else {
			LOGGER.error("User not found");
			throw new UnauthorizedException("Invalid username or password");
		}
	}

	public AuthResponse getValidity(String token) {

		String token1 = token.substring(7);
		AuthResponse authResponse = new AuthResponse();
		if (jwtUtil.validateToken(token1)) {
			LOGGER.info("Token is valid");
			authResponse.setUsername(jwtUtil.extractUsername(token1));
			authResponse.setValid(true);
			authResponse.setUsername(userRepository.findById(jwtUtil.extractUsername(token1)).get().getEmpUsername());
		} else {
			authResponse.setValid(false);
			LOGGER.error("Token Has Expired");
		}
		return authResponse;

	}
	
	public int getEmpId(String username) {
		LOGGER.info("Inside getemployeeid");
		return userRepository.findByEmpUsername(username).getEmpid();
	}
}
