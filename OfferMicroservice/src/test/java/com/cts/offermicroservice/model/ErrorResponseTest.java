package com.cts.offermicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ErrorResponseTest {

	ErrorResponse error=new ErrorResponse("abc",new ArrayList<String>());
	ErrorResponse res=new ErrorResponse();
	ArrayList<String> array=new ArrayList<String>();
	
	@Test
	public void getDetailsTest()
	{
		res.setDetails(array);
		assertEquals(res.getDetails(),array);
	}
	
	@Test
	public void getMessageTest()
	{
		res.setMessage("abc");
		assertEquals(res.getMessage(),"abc");
	}
}
