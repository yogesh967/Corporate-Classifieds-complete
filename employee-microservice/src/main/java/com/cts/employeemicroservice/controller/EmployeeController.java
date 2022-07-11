package com.cts.employeemicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/viewemployeeoffers/{id}")
	public ResponseEntity<?> viewEmployeeOffers(@RequestHeader("Authorization") String token,
			@PathVariable("id") int employeeId) {
		LOGGER.info("Inside view employee offers");
		return new ResponseEntity<>(employeeService.viewEmpOffers(token, employeeId), HttpStatus.OK);
	}

	@GetMapping("/viewprofile/{id}")
	public ResponseEntity<Employee> viewProfile(@RequestHeader("Authorization") String token,
			@PathVariable("id") int id) {
		LOGGER.info("Inside view employee profile");
		return new ResponseEntity<>(employeeService.viewEmployee(token, id), HttpStatus.OK);
	}

	@GetMapping("/viewmostlikedoffers/{id}")
	public ResponseEntity<?> viewTopOffers(@RequestHeader("Authorization") String token, @PathVariable("id") int id) {
		LOGGER.info("Inside view top offers");
		return new ResponseEntity<>(employeeService.viewTopOffers(token, id), HttpStatus.OK);
	}
}
