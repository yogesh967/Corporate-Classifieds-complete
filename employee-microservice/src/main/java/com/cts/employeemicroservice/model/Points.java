package com.cts.employeemicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Points {
	private int pointsId;
	private int empId;
	private int points;
	private int offerId;
}
