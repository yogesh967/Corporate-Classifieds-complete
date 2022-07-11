package com.cts.offermicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@EnableFeignClients
public class OfferMicroserviceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferMicroserviceApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Inside OfferMicroservice");
		SpringApplication.run(OfferMicroserviceApplication.class, args);
	}

}
