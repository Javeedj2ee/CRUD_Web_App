package com.javeed.test.dao;

import java.util.List;

import com.javeed.test.model.Employee;

public interface EmployeeDao {

	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();

	public Employee getEmployee(int empid);

}
