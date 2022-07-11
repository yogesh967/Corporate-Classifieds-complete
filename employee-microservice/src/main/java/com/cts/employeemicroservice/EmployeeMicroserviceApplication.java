package com.cts.employeemicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.cts.employeemicroservice")
public class EmployeeMicroserviceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMicroserviceApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Inside EmployeeMicroService");
		SpringApplication.run(EmployeeMicroserviceApplication.class, args);
	}

}
