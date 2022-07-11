package com.cts.offermicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${points.feign.client}", name = "${points.feign.name}")
public interface PointClient {

	@PostMapping("/refreshpointsbyemp/{empid}")
	public ResponseEntity<?> refreshPoints(@RequestHeader(name = "Authorization") String token,
			@PathVariable("empid") int empid);
}
