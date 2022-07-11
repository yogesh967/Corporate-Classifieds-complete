package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PointsTest {
	
	Points pointsOne = new Points(1,1,1,1);
	
	Points pointsTwo = new Points();
	
	
	@Test
	public void empIdTest()
	{
		pointsTwo.setEmpId(1);
		
		assertEquals(pointsTwo.getEmpId(),1);

	}
	@Test
	public void pointsIdTest()
	{
		pointsTwo.setPointsId(1);
		assertEquals(pointsTwo.getPointsId(),1);

	}
	@Test
	public void offerIdTest()
	{
		pointsTwo.setOfferId(1);
		assertEquals(pointsTwo.getOfferId(),1);
	}
	
	
	@Test
	public void pointsTest()
	{
		pointsTwo.setPoints(1);
		assertEquals(pointsTwo.getPoints(),1);

	}

}