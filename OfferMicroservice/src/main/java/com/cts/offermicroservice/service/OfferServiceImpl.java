package com.cts.offermicroservice.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.offermicroservice.client.AuthClient;
import com.cts.offermicroservice.client.PointClient;
import com.cts.offermicroservice.service.exception.CategoryNotFoundException;
import com.cts.offermicroservice.service.exception.InvalidUserException;
import com.cts.offermicroservice.model.AuthResponse;
import com.cts.offermicroservice.model.Offer;
import com.cts.offermicroservice.model.OfferCategory;
import com.cts.offermicroservice.repository.OfferCategoryRepository;
import com.cts.offermicroservice.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	AuthClient authClient;

	@Autowired
	OfferCategoryRepository offerCategoryRepository;

	@Autowired
	PointClient pointClient;

	@Override
	public Offer addOffer(String token, Offer offer) {
		LOGGER.info("Inside add offer service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			OfferCategory category = offerCategoryRepository.findById(offer.getOfferCategory().getOffCategoryId())
					.get();
			if (category != null) {
				offer.setOfferCategory(category);
				offerRepository.save(offer);
				return offer;
			} else {
				LOGGER.error("No category");
				throw new CategoryNotFoundException("Category not found");
			}
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}

	}

	@Override
	public Offer viewOffer(String token, int offerid) {
		LOGGER.info("Inside view offer service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			Offer offer = offerRepository.findById(offerid).get();
			return offer;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public List<Offer> getOfferByPostedDate(String token, String date) throws ParseException {
		LOGGER.info("Inside get offer by posted date service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			List<Offer> offerList = offerRepository.findByOpenedDate(LocalDate.parse(date));
			return offerList;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}

	}

	@Override
	public List<Offer> getOfferByCategory(String token, int catid) {
		LOGGER.info("Inside get offer by category service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			OfferCategory category = offerCategoryRepository.findById(catid).orElse(null);
			if (category != null) {
				List<Offer> offerList = offerRepository.findOfferByCategory(category.getOffCategoryId());
				return offerList;
			} else {
				LOGGER.error("No category");
				throw new CategoryNotFoundException("Category not found");
			}
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}

	}

	@Override
	public List<Offer> getOfferByTopLikes(String token) {
		LOGGER.info("Inside get offer by top likes service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			List<Offer> topLikes = offerRepository.findOfferByTopLikes();
			List<Offer> offerList = topLikes.stream().limit(3).collect(Collectors.toList());
			return offerList;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public List<Offer> getOfferByEmpId(String token, int empid) {
		LOGGER.info("Inside get offer by employee id service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		List<Offer> offer = new ArrayList<Offer>();
		if (authResponse.isValid()) {
			offer = offerRepository.findByEmpId(empid);
			return offer;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}

	}

	public Offer updateOffer(String token, int offerid, int empid) {
		LOGGER.info("Inside update offer service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			Offer offer = offerRepository.findById(offerid).orElse(null);
			if (offer != null) {
				offer.setLikes(offer.getLikes() + 1);
				offerRepository.save(offer);
				pointClient.refreshPoints(token, empid);
			}
			return offer;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public List<Offer> viewAllOffers(String token) {
		LOGGER.info("Inside view all offer service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		if (authResponse.isValid()) {
			List<Offer> offer = offerRepository.findAll();
			return offer;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public OfferCategory getCategory(String token, int catId) {
		LOGGER.info("Inside get category service");
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		OfferCategory offerCategory = null;
		if (authResponse.isValid()) {
			offerCategory = offerCategoryRepository.findById(catId).orElse(null);
			return offerCategory;
		} else {
			LOGGER.error("Invalid token");
			throw new InvalidUserException("Invalid User");
		}

	}

}
