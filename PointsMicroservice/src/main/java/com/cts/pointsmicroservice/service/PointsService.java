package com.cts.pointsmicroservice.service;

import com.cts.pointsmicroservice.model.Points;

public interface PointsService {

	public Integer getPoints(String token, int id);

	public Points refreshPoints(String token, int id);

}
