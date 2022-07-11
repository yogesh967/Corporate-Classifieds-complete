package com.cts.offermicroservice.model;


import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="offer")
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int offerId;
	@NotNull(message="Provide id")
	//@Pattern(regexp = "^/^[A-Za-z_ ]+$/")
	private String offerName;
	private LocalDate openedDate;
	private LocalDate closedDate;
	@Digits(fraction = 0, integer = 2)
	private Integer empId;
	private int likes;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="offCategoryId")
  
	private OfferCategory offerCategory;

}
