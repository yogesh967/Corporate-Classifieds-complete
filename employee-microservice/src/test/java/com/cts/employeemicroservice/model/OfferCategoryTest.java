package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OfferCategoryTest {

	OfferCategory offer=new OfferCategory();
	OfferCategory offerCategory=new OfferCategory(1,"abc");
	
	@Test
	void testOffCategoryId()
	{
		offer.setOffCategoryId(1);
		assertEquals(offer.getOffCategoryId(),1);
	}
	
	@Test
	void testOffCategoryName()
	{
		offer.setOffCategoryName("abc");
		assertEquals(offer.getOffCategoryName(),"abc");
	}
	
	@Test
	void testToString() {
		String string = offerCategory.toString();
		assertEquals(offerCategory.toString(), string);
	}
}
