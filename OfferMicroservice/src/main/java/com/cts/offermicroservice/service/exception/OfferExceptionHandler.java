package com.cts.offermicroservice.service.exception;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.offermicroservice.model.ErrorResponse;
import com.cts.offermicroservice.model.MessageResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OfferExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Checks the value of the authorization token entered by the user. If any
	 * characters are missing in the token,then it throws StringOutOfBoundsException
	 * with the message not a valid token through the response entity along with the
	 * timeStamp and httpStatus
	 * 
	 * @param StringOutOfBoundsException
	 * @return ResponseEntity<MessageResponse>
	 **/
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<?> handleStringIndexOutOfBoundException(StringIndexOutOfBoundsException sie) {
		log.error("Bad Request:Not a valid token");
		return ResponseEntity.badRequest().body(new MessageResponse("Not a valid token", "Unauthorized"));

	}

	/**
	 * Checks if microService required for the running of that particular
	 * microServices are in running state or not.If any one microService is not
	 * running,then it throws ConnectException with the message id not existing
	 * through the response entity along with the timeStamp and httpStatus
	 * 
	 * @param ConnectException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<?> handleServiceDownException(ConnectException ce) {
		log.error("Bad request:Check your Connection");
		return ResponseEntity.badRequest().body(new MessageResponse("Check your Connection", "Service unavailable"));

	}

	/**
	 * Checks the entered user token.If the token is not valid it throws
	 * InvalidUserException with the message InvalidUser through the response entity
	 * along with the timeStamp and httpStatus
	 * 
	 * @param InvalidUserException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleInvalidUserException(InvalidUserException ie) {
		log.error("Bad request:Invalid User");
		return ResponseEntity.badRequest().body(new MessageResponse(ie.getMessage(), "Unauthorized"));

	}
	
	/**
	 * Checks the entered category.If the category id is not valid it throws
	 * CategoryNotFoundException with the message Category not found through the
	 * response entity along with the timeStamp and httpStatus
	 * 
	 * @param InvalidUserException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ce) {
		log.error("Bad request:Invalid User");
		return ResponseEntity.badRequest().body(new MessageResponse(ce.getMessage(), "Not Found"));

	}

	/**
	 * Checks the constraint for validation.If the constraint validation mismatched
	 * it throws MethodArgumentNotValidException with the proper message through the
	 * response entity along with the timeStamp and httpStatus
	 * 
	 * @param  MethodArgumentNotValidException
	 * @return ResponseEntity<MessageResponse>
	 */
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Checks the entered inputs in the body.If the input  is not valid it throws
	 * HttpMessageNotReadableException with the message ad constraint through the
	 * response entity along with the timeStamp and httpStatus
	 * 
	 * @param InvalidUserException
	 * @return ResponseEntity<MessageResponse>
	 */
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exe,
															   HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("Invalid input", "Bad constraint"));
	}

}
