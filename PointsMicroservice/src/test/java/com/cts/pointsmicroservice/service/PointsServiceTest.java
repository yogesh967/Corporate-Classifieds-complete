package com.cts.pointsmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.pointsmicroservice.client.AuthClient;
import com.cts.pointsmicroservice.client.OfferClient;
import com.cts.pointsmicroservice.exception.InvalidUserException;
import com.cts.pointsmicroservice.model.AuthResponse;
import com.cts.pointsmicroservice.model.Offer;
import com.cts.pointsmicroservice.model.Points;
import com.cts.pointsmicroservice.repository.PointsRepository;

@SpringBootTest
public class PointsServiceTest {

	@InjectMocks
	private PointsServiceImpl pointServiceImpl;

	@Mock
	private AuthClient authClient;

	@Mock
	private OfferClient offerClient;

	@Mock
	private PointsRepository pointsRepository;

	@Test
	public void getPoints() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		ResponseEntity<Points> points = new ResponseEntity<>(new Points(1, 1, 0, 1), HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(pointsRepository.findById(1)).thenReturn(Optional.of(points.getBody()));
		assertEquals(pointServiceImpl.getPoints(token, 1), points.getBody().getPoints());
	}

	@Test
	public void refreshPoints() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		Points points = new Points(1, 1, 10, 1);
		ResponseEntity<List<Offer>> offer = new ResponseEntity<List<Offer>>(
				Arrays.asList(new Offer(1, "name", LocalDate.now(), LocalDate.of(2020, 12, 17), 1, 92)),
				HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerClient.getOfferByEmpId(token, 1)).thenReturn(offer);
		when(pointsRepository.getPoint(1, 1)).thenReturn(points);
		when(pointsRepository.save(points)).thenReturn(points);
		Points ansPoints = pointServiceImpl.refreshPoints(token, 1);
		assertEquals(ansPoints.getPoints(), points.getPoints());
	}

	@Test
	public void refreshPointsOne() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		Points points = new Points(2, 2, 50, 2);
		ResponseEntity<List<Offer>> offer = new ResponseEntity<List<Offer>>(
				Arrays.asList(new Offer(2, "name", LocalDate.now(), LocalDate.of(2020, 12, 17), 2, 121)),
				HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerClient.getOfferByEmpId(token, 2)).thenReturn(offer);
		when(pointsRepository.getPoint(2, 2)).thenReturn(points);
		when(pointsRepository.save(points)).thenReturn(points);
		assertEquals(pointServiceImpl.refreshPoints(token, 2).getPoints(), points.getPoints());
	}

	@Test
	public void refreshPointsTwo() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		Points pointsAnswerOne = new Points(3, 3, 50, 3);
		ResponseEntity<List<Offer>> offer = new ResponseEntity<List<Offer>>(
				Arrays.asList(new Offer(3, "name", LocalDate.now(), LocalDate.of(2020, 12, 17), 3, 121)),
				HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerClient.getOfferByEmpId(token, 3)).thenReturn(offer);
		when(pointsRepository.getPoint(3, 3)).thenReturn(pointsAnswerOne);
		when(pointsRepository.save(pointsAnswerOne)).thenReturn(pointsAnswerOne);
		assertEquals(pointServiceImpl.refreshPoints(token, 3).getPoints(), pointsAnswerOne.getPoints());
	}

	@Test
	public void refreshPointsThree() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		Points pointsAnswerOne = new Points(3, 3, 50, 3);
		ResponseEntity<List<Offer>> offer = new ResponseEntity<List<Offer>>(
				Arrays.asList(new Offer(1, "name", LocalDate.now(), LocalDate.of(2020, 12, 17), 1, 121)),
				HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerClient.getOfferByEmpId(token, 3)).thenReturn(offer);
		when(pointsRepository.getPoint(3, 3)).thenReturn(null);
		when(pointsRepository.save(new Points(3, 3, 50, 3))).thenReturn(new Points(3, 3, 50, 3));
		assertEquals(pointServiceImpl.refreshPoints(token, 3).getPoints(), pointsAnswerOne.getPoints());
	}

	@Test
	public void refreshPointsFour() {
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1, "user", true),
				HttpStatus.OK);
		Points pointsAnswerOne = new Points(3, 3, 10, 3);
		ResponseEntity<List<Offer>> offer = new ResponseEntity<List<Offer>>(
				Arrays.asList(new Offer(1, "name", LocalDate.now(), LocalDate.of(2020, 12, 17), 1, 52)),
				HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerClient.getOfferByEmpId(token, 3)).thenReturn(offer);
		when(pointsRepository.getPoint(3, 3)).thenReturn(null);
		when(pointsRepository.save(new Points(3, 3, 10, 3))).thenReturn(new Points(3, 3, 10, 3));
		assertEquals(pointServiceImpl.refreshPoints(token, 3).getPoints(), pointsAnswerOne.getPoints());
	}

	@Test
	public void authenticationFailTest() {
		ResponseEntity<AuthResponse> auth = new ResponseEntity<>(new AuthResponse(1, "user", false), HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class, () -> pointServiceImpl.getPoints("token", 1));
	}

	@Test
	public void authenticationFailTestTwo() {
		ResponseEntity<AuthResponse> auth = new ResponseEntity<>(new AuthResponse(1, "user", false), HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class, () -> pointServiceImpl.refreshPoints("token", 1));
	}

}
