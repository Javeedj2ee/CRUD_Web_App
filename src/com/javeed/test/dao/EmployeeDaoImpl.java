package com.javeed.test.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javeed.test.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployeess() {
		return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
	}

	@Override
	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
	}
	

}
