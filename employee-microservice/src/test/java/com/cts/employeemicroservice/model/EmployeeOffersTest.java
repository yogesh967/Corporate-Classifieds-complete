package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EmployeeOffersTest {

	EmployeeOffers offers=new EmployeeOffers();
	EmployeeOffers empOffers=new EmployeeOffers(1,"abc",LocalDate.now(),LocalDate.now(),1,1,new OfferCategory(1,"abc"));
	
	@Test
	void testOfferId()
	{
		offers.setOfferId(1);
		assertEquals(offers.getOfferId(),1);
	}
	
	@Test
	void testOfferName()
	{
		offers.setOfferName("abc");
		assertEquals(offers.getOfferName(),"abc");
	}
	
	@Test
	void testOpenedDate()
	{
		offers.setOpenedDate(LocalDate.now());
		assertEquals(offers.getOpenedDate(),LocalDate.now());
	}
	
	@Test
	void testClosedDate()
	{
		offers.setClosedDate(LocalDate.now());
		assertEquals(offers.getClosedDate(),LocalDate.now());
	}
	
	@Test
	void testEmpId()
	{
		offers.setEmpId(1);
		assertEquals(offers.getEmpId(),1);
	}
	
	@Test
	void testLikes()
	{
		offers.setLikes(1);
		assertEquals(offers.getLikes(),1);
	}
	
	@Test
	void testOfferCategory()
	{
		OfferCategory category=new OfferCategory(1,"abc");
		offers.setOfferCategory(category);
		assertEquals(offers.getOfferCategory(),category);
	}
	
	@Test
	void testToString() {
		String string = empOffers.toString();
		assertEquals(empOffers.toString(), string);
	}
}

