package com.cts.employeemicroservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.employeemicroservice.client.AuthClient;
import com.cts.employeemicroservice.client.OfferClient;
import com.cts.employeemicroservice.exception.InvalidUserException;
import com.cts.employeemicroservice.model.AuthResponse;
import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.model.EmployeeOffers;
import com.cts.employeemicroservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	AuthClient authClient;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	OfferClient offerClient;

	@Override
	public List<EmployeeOffers> viewEmpOffers(String token, int id) {
		LOGGER.info("Inside view employee offers");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) {
			List<EmployeeOffers> empOffers = offerClient.getOfferByEmpId(token, id).getBody();
			return empOffers;
		} else {
			LOGGER.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public Employee viewEmployee(String token, int id) {
		LOGGER.info("Inside view employee");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) {
			Employee employee = employeeRepository.findById(id).orElse(null);
			if (employee == null) {
				LOGGER.error("Invalid employee id");
				throw new NoSuchElementException();
			}
			return employee;
		} else {
			LOGGER.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public List<EmployeeOffers> viewTopOffers(String token, int employeeId) {
		LOGGER.info("Inside view top offers");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) {
			List<EmployeeOffers> emp = offerClient.getOfferByTopLikes(token).getBody();
			List<EmployeeOffers> empList = emp.stream().filter(x -> x.getEmpId() == employeeId)
					.collect(Collectors.toList());
			return empList;
		} else {
			LOGGER.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}
}
