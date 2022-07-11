package com.cts.offermicroservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.offermicroservice.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	public List<Offer> findByOpenedDate(LocalDate localDate);

	@Query(value = "from Offer o where o.offerCategory.offCategoryId=?1")
	public List<Offer> findOfferByCategory(int id);

	@Query(value = "from Offer o order by o.likes desc")
	public List<Offer> findOfferByTopLikes();

	public List<Offer> findByEmpId(int id);

}
