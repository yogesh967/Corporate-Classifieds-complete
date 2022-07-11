package com.cts.offermicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OfferCategoryTest {

	OfferCategory offerCategory =new OfferCategory();
	OfferCategory category=new OfferCategory(1,"abc");
	
	@Test
	void testOffCategoryId() {
		offerCategory.setOffCategoryId(1);
		assertEquals(offerCategory.getOffCategoryId(),1);
	}
	
	@Test
	void testOffCategoryName() {
		offerCategory.setOffCategoryName("abc");
		assertEquals(offerCategory.getOffCategoryName(),"abc");
	}
	
	@Test
	void testToString() {
		String string = category.toString();
		assertEquals(category.toString(), string);
	}
}
