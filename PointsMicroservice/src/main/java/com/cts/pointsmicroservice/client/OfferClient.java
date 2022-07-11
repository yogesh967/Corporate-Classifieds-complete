package com.cts.pointsmicroservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.pointsmicroservice.model.Offer;

@FeignClient(url = "${offer.feign.client}", name = "${offer.feign.name}")
public interface OfferClient {

	@GetMapping("/getofferbyemp/{id}")
	public ResponseEntity<List<Offer>> getOfferByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") int id);

}
