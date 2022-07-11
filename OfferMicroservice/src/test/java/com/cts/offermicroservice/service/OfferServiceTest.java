package com.cts.offermicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.cts.offermicroservice.OfferMicroserviceApplication;
import com.cts.offermicroservice.client.AuthClient;
import com.cts.offermicroservice.client.PointClient;
import com.cts.offermicroservice.service.exception.CategoryNotFoundException;
import com.cts.offermicroservice.service.exception.InvalidUserException;
import com.cts.offermicroservice.model.AuthResponse;
import com.cts.offermicroservice.model.Offer;
import com.cts.offermicroservice.model.OfferCategory;
import com.cts.offermicroservice.repository.OfferCategoryRepository;
import com.cts.offermicroservice.repository.OfferRepository;

@SpringBootTest
@ContextConfiguration(classes=OfferMicroserviceApplication.class)
public class OfferServiceTest {
	
	@Mock
	private OfferRepository offerRepository;

	@Mock
	private AuthClient authClient;

	@Mock
	private OfferCategoryRepository offerCategoryRepository;

	@Mock
	private PointClient pointClient;
	
	@InjectMocks
	private OfferServiceImpl offerServiceImpl;
	
	@Test
	public void viewOffersTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offer = new Offer(1,"name",LocalDate.now(),LocalDate.now(),1,5,new OfferCategory(1,"name"));
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findById(1)).thenReturn(Optional.of(offer));
		assertEquals(offerServiceImpl.viewOffer(token, 1),offer);
	}
	
	@Test
	public void  viewAllOffersTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(offerOne);			
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findAll()).thenReturn(offer);
		assertEquals(offerServiceImpl.viewAllOffers(token),offer);
	}
	
	@Test
	public void  getCategoryTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		OfferCategory offerCategory =new OfferCategory(1,"name");		
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerCategoryRepository.findById(1)).thenReturn(Optional.of(offerCategory));
		assertEquals(offerServiceImpl.getCategory(token, 1),offerCategory);
	}
	
	
	@Test
	public void  getOfferByTopLikesTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(offerOne);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findOfferByTopLikes()).thenReturn(offer);
		assertEquals(offerServiceImpl.getOfferByTopLikes(token),offer);
	}
	
	
	@Test
	public void  getOfferByEmpIdTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(offerOne);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findByEmpId(1)).thenReturn(offer);
		assertEquals(offerServiceImpl.getOfferByEmpId(token, 1),offer);
	}
	
	
	@Test
	public void getOfferByCategoryTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";		
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);		
		OfferCategory offerCategoryObject =new OfferCategory(1,"name");
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,offerCategoryObject);
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(offerOne);
		when(offerCategoryRepository.findById(1)).thenReturn(Optional.of(offerCategoryObject));
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findOfferByCategory(1)).thenReturn(offer);
		OfferCategory offerCategory = offerCategoryRepository.findById(1).orElse(null);
		assertEquals(offerServiceImpl.getOfferByCategory(token,offerCategory.getOffCategoryId()),offer);
	}

	@Test
	public void addOfferTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";	
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		OfferCategory offerCategoryObject = new OfferCategory(1,"name");
		when(offerCategoryRepository.findById(1)).thenReturn(Optional.of(offerCategoryObject));
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.save(offerOne)).thenReturn(offerOne);
		assertEquals(offerServiceImpl.addOffer(token, offerOne),offerOne);
	}
	
	@Test
	public void authenticationFailTest()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.addOffer("token", new Offer()));
	}
	
	@Test
	public void authenticationFailTestOne()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.viewOffer("token",1));
	}
	
	@Test
	public void authenticationFailTestTwo()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.getOfferByPostedDate("token","2020-14-20"));
	}
	
	@Test
	public void authenticationFailTestThree()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.getOfferByCategory("token", 1));
	}
	
	@Test
	public void authenticationFailTestFour()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.getOfferByTopLikes("token"));
	}
	
	@Test
	public void authenticationFailTestFive()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.getOfferByEmpId("token", 1));
	}
	
	@Test
	public void authenticationFailTestSix()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.updateOffer("token",1,1));
	}
	
	@Test
	public void authenticationFailTestSeven()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.viewAllOffers("token"));
	}
	
	@Test
	public void authenticationFailTestEight()
	{
		ResponseEntity<AuthResponse> auth= new ResponseEntity<>(new AuthResponse(1,"admin", false),HttpStatus.OK);
		when(authClient.verifyToken("token")).thenReturn(auth);
		assertThrows(InvalidUserException.class,()->offerServiceImpl.getCategory("token",1));
	}
	
	@Test
	public void categoryNotFoundTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";	
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"abc"));
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerCategoryRepository.findById(1)).thenReturn(null);
		offerOne.setOfferCategory(null);
		assertThrows(NullPointerException.class,()->offerServiceImpl.addOffer(token, offerOne));
	}
	
	@Test
	public void categoryNotFoundTestOne()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";	
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerCategoryRepository.findById(1)).thenReturn(null);
		assertThrows(CategoryNotFoundException.class,()->offerServiceImpl.getOfferByCategory(token, 2));
	}
	
	@Test
	public void getOfferByPostedDateTest() throws ParseException
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne = new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		List<Offer> offer = new ArrayList<Offer>();
		offer.add(offerOne);
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findByOpenedDate(LocalDate.of(2020,12,12))).thenReturn(offer);
		assertEquals(offerServiceImpl.getOfferByPostedDate(token,"2020-12-12"),offer);
	}
	
	@Test
	public void updateOfferTest()
	{
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		ResponseEntity<AuthResponse> authResponse = new ResponseEntity<>(new AuthResponse(1,"user", true), HttpStatus.OK);
		Offer offerOne =new Offer(1,"name",LocalDate.of(2020,12,12),LocalDate.now(),1,92,new OfferCategory(1,"name"));
		when(authClient.verifyToken(token)).thenReturn(authResponse);
		when(offerRepository.findById(1)).thenReturn(Optional.of(offerOne));
		when(offerRepository.save(offerOne)).thenReturn(offerOne);
		assertEquals(offerServiceImpl.updateOffer(token, 1, 1),offerOne);
	}
}
