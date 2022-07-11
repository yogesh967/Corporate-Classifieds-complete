package com.cts.offermicroservice.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.offermicroservice.client.AuthClient;
import com.cts.offermicroservice.model.Offer;
import com.cts.offermicroservice.model.OfferCategory;
import com.cts.offermicroservice.service.OfferService;

@RestController
@CrossOrigin(origins="*")
public class OfferController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	OfferService offerService;

	@Autowired
	AuthClient authClient;
	@PostMapping("/saveoffer")
	public ResponseEntity<?> addOffer(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody Offer offer) {
		LOGGER.info("Inside add offer");
		return new ResponseEntity<>(offerService.addOffer(token, offer), HttpStatus.OK);
	}

	@PostMapping("/updateoffer/{offerid}/{empid}")
	public ResponseEntity<?> updateOffer(@RequestHeader(name = "Authorization") String token,
			@PathVariable("offerid") int offerid, @PathVariable("empid") int empid) {
		LOGGER.info("Inside update offer");
		return new ResponseEntity<>(offerService.updateOffer(token, offerid, empid), HttpStatus.OK);
	}

	@GetMapping("/getoffer/{offerid}")
	public ResponseEntity<?> getOfferDetails(@RequestHeader(name = "Authorization") String token,
			@PathVariable("offerid") int offerid) {
		LOGGER.info("Inside offer details");
		return new ResponseEntity<>(offerService.viewOffer(token, offerid), HttpStatus.OK);
	}

	@GetMapping("/getofferbydate/{date}")
	public ResponseEntity<List<Offer>> getOfferByPostedDate(@RequestHeader(name = "Authorization") String token,
			@PathVariable("date") String date) throws ParseException {
		LOGGER.info("Inside get offer by date");
		return new ResponseEntity<>(offerService.getOfferByPostedDate(token, date), HttpStatus.OK);
	}

	@GetMapping("/getofferbycategory/{catid}")
	public ResponseEntity<List<Offer>> getOfferByCategory(@RequestHeader(name = "Authorization") String token,
			@PathVariable("catid") int catid) {
		LOGGER.info("Inside get offer by category");
		return new ResponseEntity<>(offerService.getOfferByCategory(token, catid), HttpStatus.OK);
	}

	@GetMapping("/getofferbylikes")
	public ResponseEntity<List<Offer>> getOfferByTopLikes(@RequestHeader(name = "Authorization") String token) {
		LOGGER.info("Inside get offer by top likes");
		return new ResponseEntity<>(offerService.getOfferByTopLikes(token), HttpStatus.OK);
	}

	@GetMapping("/getofferbyemp/{empid}")
	public ResponseEntity<List<Offer>> getOfferByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("empid") int empid) {
		LOGGER.info("Inside get offer by employee id");
		return new ResponseEntity<>(offerService.getOfferByEmpId(token, empid), HttpStatus.OK);
	}

	@GetMapping("/getalloffers")
	public ResponseEntity<List<Offer>> getAllOffers(@RequestHeader(name = "Authorization") String token) {
		LOGGER.info("Inside get all offers");
		return new ResponseEntity<>(offerService.viewAllOffers(token), HttpStatus.OK);
	}

	@GetMapping("/getcategory/{catid}")
	public ResponseEntity<OfferCategory> getCategory(@RequestHeader(name = "Authorization") String token,
			@PathVariable("catid") int catId) {
		LOGGER.info("Inside get category");
		return new ResponseEntity<>(offerService.getCategory(token, catId), HttpStatus.OK);
	}

}
