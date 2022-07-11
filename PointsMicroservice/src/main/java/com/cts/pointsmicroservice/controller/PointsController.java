package com.cts.pointsmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.pointsmicroservice.service.PointsService;

@RestController
@CrossOrigin(origins = "*")
public class PointsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PointsController.class);

	@Autowired
	PointsService pointsService;

	@GetMapping("/getpointsbyemp/{id}")
	public ResponseEntity<Integer> getPointsByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id) {
		LOGGER.info("Inside getpointsbyemployeeid");
		return new ResponseEntity<>(pointsService.getPoints(token, id), HttpStatus.OK);
	}

	@PostMapping("/refreshpointsbyemp/{id}")
	public ResponseEntity<?> refreshPointsByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id) {
		LOGGER.info("Inside refreshpoints");
		return new ResponseEntity<>(pointsService.refreshPoints(token, id), HttpStatus.OK);
	}
}
