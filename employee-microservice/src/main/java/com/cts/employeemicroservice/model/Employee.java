package com.cts.employeemicroservice.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="employee")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
	
	@Id
	private int empId;
	private String empName;
	private String empDepartment;
	private String empGender;
	private int empAge;
	private int empContact;
	private String empEmail;

}
