package com.cts.employeemicroservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.employeemicroservice.model.EmployeeOffers;

@FeignClient(url = "${offer.feign.client}", name = "${offer.feign.name}")
public interface OfferClient {

	@GetMapping("/getofferbylikes")
	public  ResponseEntity<List<EmployeeOffers>> getOfferByTopLikes(@RequestHeader(name = "Authorization") String token);

	@GetMapping("/getoffer/{id}")
	public ResponseEntity<?> getOfferDetails(@RequestHeader(name = "Authorization") String token,@PathVariable("id") int id);

	@GetMapping("/getofferbyemp/{id}")
	public ResponseEntity<List<EmployeeOffers>> getOfferByEmpId(@RequestHeader(name = "Authorization") String token,@PathVariable("id") int id);
}
