package com.cts.offermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.offermicroservice.model.OfferCategory;

@Repository
public interface OfferCategoryRepository extends JpaRepository<OfferCategory,Integer> {
	
}
