package com.cts.offermicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthResponseTest {

	AuthResponse auth= new AuthResponse();
	AuthResponse auth1= new AuthResponse(1,"ad",true);
	
	@Test
	void testUserName() 
	{
		auth.setName("a");
		assertEquals(auth.getName(), "a");
	}
	
	@Test
	void testEmpId() {
		auth.setUid(1);
		assertEquals(auth.getUid(),1);
	}
	
	@Test
	void testIsValid() {
		auth.setValid(true);
		assertEquals(auth.isValid(), true);
	}
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(), string);
	}
}
