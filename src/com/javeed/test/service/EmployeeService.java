package com.javeed.test.service;

import java.util.List;

import com.javeed.test.model.Employee;

public interface EmployeeService {
 
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();

	public Employee getEmployee(int empid);

}
