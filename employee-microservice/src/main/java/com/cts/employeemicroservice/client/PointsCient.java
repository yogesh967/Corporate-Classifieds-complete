package com.cts.employeemicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${points.feign.client}", name = "${points.feign.name}")
public interface PointsCient {
	@GetMapping("/getPointsByEmp/{id}")
	public ResponseEntity<Integer> getPointsByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id);

}
