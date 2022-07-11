package com.cts.employeemicroservice.service;

import java.util.List;

import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.model.EmployeeOffers;

public interface EmployeeService {

	public List<EmployeeOffers> viewEmpOffers(String token, int employeeId);

	public List<EmployeeOffers> viewTopOffers(String token, int employeeId);

	public Employee viewEmployee(String token, int id);

}
