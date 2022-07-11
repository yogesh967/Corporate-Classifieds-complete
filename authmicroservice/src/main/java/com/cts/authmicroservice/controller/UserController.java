package com.cts.authmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.UserModel;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.service.UserServiceImpl;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody UserModel userModel) {
		LOGGER.info("Inside Login : ");
		return new ResponseEntity<UserToken>(userServiceImpl.login(userModel), HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
		LOGGER.info("Inside Token Validation ");
		return new ResponseEntity<AuthResponse>(userServiceImpl.getValidity(token), HttpStatus.OK);
	}

	@GetMapping("/getempid")
	public int getEmpId(String username) {
		LOGGER.info("Inside get employee id");
		return userServiceImpl.getEmpId(username);
	}
}
