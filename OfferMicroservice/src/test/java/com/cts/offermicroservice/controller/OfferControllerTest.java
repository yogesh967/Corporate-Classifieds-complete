package com.cts.offermicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cts.offermicroservice.client.AuthClient;
import com.cts.offermicroservice.client.PointClient;
import com.cts.offermicroservice.model.AuthResponse;
import com.cts.offermicroservice.model.Offer;
import com.cts.offermicroservice.model.OfferCategory;
import com.cts.offermicroservice.service.OfferService;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

	@InjectMocks
	private OfferController offerController;

	@Mock
	private OfferService offerService;

	@MockBean
	private AuthClient authClient;

	@MockBean
	private PointClient pointsClient;

	@Test
	public void addOffer() throws JsonProcessingException, Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		Offer offer = new Offer(1, "name", LocalDate.now(), LocalDate.now(), 1, 5, new OfferCategory(1, "Vehicle"));
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.addOffer(token, offer)).thenReturn(offer);
		assertEquals(200, offerController.addOffer(token, offer).getStatusCodeValue());
	}

	@Test
	public void updateOffer() throws JsonProcessingException, Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Vehicle"));
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.updateOffer(token, 1, 1)).thenReturn(offer);
		assertEquals(200, offerController.updateOffer(token, 1, 1).getStatusCodeValue());
	}

	@Test
	public void getOfferDetails() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Vehicle"));
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.viewOffer(token, 1)).thenReturn(offer);
		assertEquals(200, offerController.getOfferDetails(token, 1).getStatusCodeValue());

	}

	@Test
	public void getOfferByPostedDate() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		List<Offer> offerList=new ArrayList<Offer>();
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Vehicle"));
		offerList.add(offer);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.getOfferByPostedDate(token,"2020-12-14")).thenReturn(offerList);
		assertEquals(200,offerController.getOfferByPostedDate(token, "2020-12-14").getStatusCodeValue());
	}

	@Test
	public void getOfferByCategory() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		List<Offer> offerList=new ArrayList<Offer>();
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Vehicle"));
		offerList.add(offer);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.getOfferByCategory(token, 1)).thenReturn(offerList);
		assertEquals(200, offerController.getOfferByCategory(token, 1).getStatusCodeValue());
	}

	@Test
	public void getOfferByLikes() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		List<Offer> offerList=new ArrayList<Offer>();
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Vehicle"));
		offerList.add(offer);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.getOfferByTopLikes(token)).thenReturn(offerList);
		assertEquals(200, offerController.getOfferByTopLikes(token).getStatusCodeValue());
	}

	@Test
	public void getOfferByEmpId() throws Exception {
		List<Offer> offerList = new ArrayList<Offer>();
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(121, "Veichle"));
		offerList.add(offer);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.getOfferByEmpId(token, 1)).thenReturn(offerList);
		assertEquals(200, offerController.getOfferByEmpId(token, 1).getStatusCodeValue());
	}

	@Test
	public void getAllOffer() throws Exception {
		List<Offer> offerList = new ArrayList<Offer>();
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		Offer offer = new Offer(1, "name", LocalDate.of(2017, 1, 13), LocalDate.of(2017, 1, 13), 1, 1,
				new OfferCategory(1, "name"));
		offerList.add(offer);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.viewAllOffers(token)).thenReturn(offerList);
		assertEquals(200, offerController.getAllOffers(token).getStatusCodeValue());
	}

	@Test
	public void getCategory() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		OfferCategory category=new OfferCategory(1,"abc");
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerService.getCategory(token, 1)).thenReturn(category);
		assertEquals(200, offerController.getCategory(token, 1).getStatusCodeValue());
	}
}