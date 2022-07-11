package com.cts.employeemicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;

import com.cts.employeemicroservice.client.AuthClient;
import com.cts.employeemicroservice.client.OfferClient;
import com.cts.employeemicroservice.model.AuthResponse;
import com.cts.employeemicroservice.model.EmployeeOffers;
import com.cts.employeemicroservice.model.OfferCategory;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	AuthClient authClient;
	
	@MockBean
	OfferClient offerClient;
	
	@Test
	public void testViewEmpOffers() throws Exception
	{
		ResponseEntity<AuthResponse> auth = new ResponseEntity<>(new AuthResponse (1, "abc", true),HttpStatus.OK);
		List<EmployeeOffers> emp=new ArrayList<EmployeeOffers>();
		emp.add(new EmployeeOffers(1, "abc",LocalDate.now(),LocalDate.now(),1,1,new OfferCategory(1,"xyz")));
		ResponseEntity<List<EmployeeOffers>> employeeList=new ResponseEntity<>(emp,HttpStatus.OK);
		String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3NTEzMDYzLCJpYXQiOjE2MDc1MTIxNjN9.58WkfUnr0paJU_bxFY4B0a2VfKV14IGL8pBtpRGwDWA";
		when(authClient.getValidity(token)).thenReturn(auth);
		when(offerClient.getOfferByEmpId(token, 1)).thenReturn(employeeList);
		MvcResult mvcResult =mockMvc.perform(get("/viewemployeeoffers/{id}",1).header("Authorization",token)).andReturn(); 
		int actualvalue=mvcResult.getResponse().getStatus();
		assertEquals(200,actualvalue);
	}
	
	@Test
	public void testViewProfile() throws Exception
	{
		ResponseEntity<AuthResponse> auth = new ResponseEntity<>(new AuthResponse (1, "abc", true),HttpStatus.OK);
		String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3NTEzMDYzLCJpYXQiOjE2MDc1MTIxNjN9.58WkfUnr0paJU_bxFY4B0a2VfKV14IGL8pBtpRGwDWA";
		when(authClient.getValidity(token)).thenReturn(auth);
		MvcResult mvcResult =mockMvc.perform(get("/viewprofile/{id}",1).header("Authorization",token)).andReturn(); 
		int actualvalue=mvcResult.getResponse().getStatus();
		assertEquals(200,actualvalue);
	}
	
	@Test
	public void testViewTopOffers() throws Exception
	{
		ResponseEntity<AuthResponse> auth = new ResponseEntity<>(new AuthResponse (1, "abc", true),HttpStatus.OK);
		List<EmployeeOffers> emp=new ArrayList<EmployeeOffers>();
		emp.add(new EmployeeOffers(1, "abc",LocalDate.now(),LocalDate.now(),1,1,new OfferCategory(1,"xyz")));
		ResponseEntity<List<EmployeeOffers>> employee=new ResponseEntity<>(emp,HttpStatus.OK);
		String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3NTEzMDYzLCJpYXQiOjE2MDc1MTIxNjN9.58WkfUnr0paJU_bxFY4B0a2VfKV14IGL8pBtpRGwDWA";
		when(authClient.getValidity(token)).thenReturn(auth);
		when(offerClient.getOfferByTopLikes(token)).thenReturn(employee);
		MvcResult mvcResult =mockMvc.perform(get("/viewmostlikedoffers/{id}",1).header("Authorization",token)).andReturn(); 
		int actualvalue=mvcResult.getResponse().getStatus();
		assertEquals(200,actualvalue);
	}
}
