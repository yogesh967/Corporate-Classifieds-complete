package com.cts.pointsmicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class MessageResponseTest {

	MessageResponse messageResponse = new MessageResponse();
	MessageResponse msgResponse = new MessageResponse(new Date(),"abc","abc");
	MessageResponse msgRes = new MessageResponse("abc","abc");
	Date date=new Date();
	
	@Test
	void testMessage() {
		messageResponse.setMessage("abc");
		assertEquals(messageResponse.getMessage(), "abc");
	}
	
	@Test
	void testStatus() {
		messageResponse.setStatus("abc");
		assertEquals(messageResponse.getStatus(), "abc");
	}
	
	@Test
	void testTimeStamp() {
		messageResponse.setTimeStamp(date);
		assertEquals(messageResponse.getTimeStamp(),date);
	}
}
