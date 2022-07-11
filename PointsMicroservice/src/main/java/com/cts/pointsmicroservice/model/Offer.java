package com.cts.pointsmicroservice.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Offer {

	private int offerId;
	private String offerName;
	private LocalDate openedDate;
	private LocalDate closedDate;
	private int empId;
	private int likes;

}
