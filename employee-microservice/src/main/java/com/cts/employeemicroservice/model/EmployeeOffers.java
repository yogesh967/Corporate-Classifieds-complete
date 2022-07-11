package com.cts.employeemicroservice.model;

import java.time.LocalDate;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeOffers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int offerId;
	private String offerName;
	private LocalDate openedDate;
	private LocalDate closedDate;
	private int empId;
	private int likes;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="offCategoryId")
	private OfferCategory offerCategory;
}
