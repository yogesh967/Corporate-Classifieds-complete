package com.cts.offermicroservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.cts.offermicroservice.service.exception.InvalidUserException;
import com.cts.offermicroservice.service.exception.CategoryNotFoundException;
import com.cts.offermicroservice.service.exception.OfferExceptionHandler;

import feign.FeignException;

@SpringBootTest
public class OfferExceptionHandlerTest {

	@InjectMocks
	OfferExceptionHandler offerExceptionHandler;
	
	FeignException feignException;
	
	@Test
	public void handleStringIndexOutOfBoundExceptionTest() {
		assertEquals(offerExceptionHandler.handleStringIndexOutOfBoundException(new StringIndexOutOfBoundsException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleServiceDownExceptionTest() {
		assertEquals(offerExceptionHandler.handleServiceDownException(new ConnectException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleInvalidUserExceptionTest() {
		assertEquals(offerExceptionHandler.handleInvalidUserException(new InvalidUserException(null)).getStatusCodeValue(), 400);
	}
	
	@Test
	public void handleCategoryNotFoundExceptionTest() {
		assertEquals(offerExceptionHandler.handleCategoryNotFoundException(new CategoryNotFoundException(null)).getStatusCodeValue(), 400);
	}
	
	/*
	 * @Test public void handleMethodArgumentNotValidTest() {
	 * assertEquals(offerExceptionHandler.handleMethodArgumentNotValid(new
	 * MethodArgumentNotValidException(null, null), null, null,
	 * null).getStatusCodeValue(), 400); }
	 */
	
	@Test
	public void handleHttpMessageNotReadableTest() {
		assertEquals(offerExceptionHandler.handleHttpMessageNotReadable(new HttpMessageNotReadableException(null, null, null), null, null, null).getStatusCodeValue(), 400);
	}
}
