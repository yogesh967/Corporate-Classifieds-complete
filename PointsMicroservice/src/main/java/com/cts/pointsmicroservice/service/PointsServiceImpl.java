package com.cts.pointsmicroservice.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pointsmicroservice.client.AuthClient;
import com.cts.pointsmicroservice.client.OfferClient;
import com.cts.pointsmicroservice.exception.InvalidUserException;
import com.cts.pointsmicroservice.model.AuthResponse;
import com.cts.pointsmicroservice.model.Offer;
import com.cts.pointsmicroservice.model.Points;
import com.cts.pointsmicroservice.repository.PointsRepository;

@Service
public class PointsServiceImpl implements PointsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PointsServiceImpl.class);

	@Autowired
	AuthClient authClient;

	@Autowired
	OfferClient offerClient;

	@Autowired
	PointsRepository pointsRepository;

	@Override
	public Integer getPoints(String token, int id) {
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		LOGGER.info("Inside getpoints");
		if (authResponse.isValid()) {
			Integer points = pointsRepository.getPointById(id);
			if (points == null) {
				points = 0;
			}
			return points;
		} else {
			LOGGER.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public Points refreshPoints(String token, int id) {
		AuthResponse authResponse = authClient.verifyToken(token).getBody();
		LOGGER.info("Inside refreshpoints");
		Points points = null;
		if (authResponse.isValid()) {
			List<Offer> offerList = offerClient.getOfferByEmpId(token, id).getBody();
			for (Offer offer : offerList) {
				if (LocalDate.now().compareTo(offer.getOpenedDate()) <= 2) {
					if (offer.getLikes() > 100) {
						points = pointsRepository.getPoint(id, offer.getOfferId());
						if (points != null) {
							points.setPoints(50);
							pointsRepository.save(points);
						} else {
							points = new Points();
							points.setPoints(50);
							points.setEmpId(id);
							points.setOfferId(offer.getOfferId());
							pointsRepository.save(points);
						}
					} else if (offer.getLikes() > 50) {
						points = pointsRepository.getPoint(id, offer.getOfferId());
						if (points != null) {
							points.setPoints(10);
							pointsRepository.save(points);
						} else {
							points = new Points();
							points.setPoints(10);
							points.setEmpId(id);
							points.setOfferId(offer.getOfferId());
							pointsRepository.save(points);
						}
					}
				}
			}
			return points;
		} else {
			LOGGER.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

}
