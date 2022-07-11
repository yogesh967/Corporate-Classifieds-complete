package com.cts.pointsmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.pointsmicroservice.model.Points;

@Repository
public interface PointsRepository extends JpaRepository<Points, Integer> {

	@Query(value = "from Points p where p.offerId=?1 and p.empId=?1")
	public Points getPoint(int empId, int offerId);

	@Query(value = "select sum(p.points) from Points p where p.empId=?1")
	public Integer getPointById(int empId);
}
