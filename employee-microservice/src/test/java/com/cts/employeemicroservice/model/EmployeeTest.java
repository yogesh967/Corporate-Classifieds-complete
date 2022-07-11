package com.cts.employeemicroservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

	Employee emp=new Employee();
	public Employee employee=new Employee(1,"abc","IT","Male",26, 1234567890,"abc@gmail.com");
	
	@Test
	void testEmpId()
	{
		emp.setEmpId(1);
		assertEquals(emp.getEmpId(),1);
	}
	
	@Test
	void testEmpName()
	{
		emp.setEmpName("abc");
		assertEquals(emp.getEmpName(),"abc");
	}
	
	@Test
	void testEmpEmail()
	{
		emp.setEmpEmail("abc@gmail.com");
		assertEquals(emp.getEmpEmail(),"abc@gmail.com");
	}
	
	@Test
	void testToString() {
		String string = employee.toString();
		assertEquals(employee.toString(), string);
	}
}
