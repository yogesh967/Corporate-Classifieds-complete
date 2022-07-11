package com.cts.authmicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthmicroserviceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthmicroserviceApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Inside auth microservice");
		SpringApplication.run(AuthmicroserviceApplication.class, args);
	}

}
