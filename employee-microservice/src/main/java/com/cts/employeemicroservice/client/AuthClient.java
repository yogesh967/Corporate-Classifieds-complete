package com.cts.employeemicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.employeemicroservice.model.AuthResponse;


@FeignClient(url = "${auth.feign.client}", name = "${auth.feign.name}")
public interface AuthClient {

	@RequestMapping(path="/validate",method=RequestMethod.GET)
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader(name="Authorization",required=true)String token);
}
