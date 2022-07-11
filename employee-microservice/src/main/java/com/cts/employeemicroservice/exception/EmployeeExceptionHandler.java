package com.cts.employeemicroservice.exception;

import java.net.ConnectException;
import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.employeemicroservice.model.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleUserNotFoundException(NullPointerException ce) {
		log.error("Bad request:Employee Details not found");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee Details not Found", "Unauthorized"));

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<?> handleStringIndexOutOfBoundException(StringIndexOutOfBoundsException sie) {
		log.error("Bad Request:Not a valid token");
		return ResponseEntity.badRequest().body(new MessageResponse("Not a valid token", "Unauthorized"));

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleFeignException(FeignException fe) {
		log.error("Bad request:Service Unavailable");
		return ResponseEntity.badRequest().body(new MessageResponse("Service Unavailable", "Service Unavailable"));

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ere) {
		log.error("Bad request:Employee ID not exist");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee ID not exist", "Not Found"));

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException nsee) {
		log.error("Bad request:Employee ID not exist");
		return ResponseEntity.badRequest().body(new MessageResponse("Employee ID not exist", "Not Found"));

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleServiceDownException(ConnectException ce) {
		log.error("Bad request:Check your Connection");
		return ResponseEntity.badRequest().body(new MessageResponse("Check your Connection", "Service unavailable"));

	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleInvalidUserException(InvalidUserException ie) {
		log.error("Bad request:Invalid User");
		return ResponseEntity.badRequest().body(new MessageResponse(ie.getMessage(), "Unauthorized"));
	}
}
