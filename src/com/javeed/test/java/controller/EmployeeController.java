
package com.javeed.test.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javeed.test.bean.EmployeeBean;
import com.javeed.test.model.Employee;
import com.javeed.test.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") EmployeeBean employeebean, BindingResult result) {
		Employee employee = prepareModel(employeebean);
		employeeservice.addEmployee(employee);
		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", prepareListofBean(employeeservice.listEmployeess()));
		return new ModelAndView("employeesList", model);

	}

	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public ModelAndView addEmployees(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", prepareListofBean(employeeservice.listEmployeess()));
		return new ModelAndView("addEmployees", model);

	}

	public List<EmployeeBean> prepareListofBean(List<Employee> employees) {
		List<EmployeeBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for (Employee employee : employees) {
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getEmpAge());
				beans.add(bean);
			}
		}
		return beans;
	}

	private Employee prepareModel(EmployeeBean employeebean) {
		Employee employee = new Employee();
		employee.setEmpId(employeebean.getId());
		employee.setEmpAddress(employeebean.getAddress());
		employee.setEmpAge(employeebean.getAge());
		employee.setEmpName(employeebean.getName());
		employee.setSalary(employeebean.getSalary());
		return employee;
	}

	private EmployeeBean prepareEmployeeBean(Employee employee) {
		EmployeeBean bean = new EmployeeBean();
		bean.setAddress(employee.getEmpAddress());
		bean.setAge(employee.getEmpAge());
		bean.setName(employee.getEmpName());
		bean.setSalary(employee.getSalary());
		bean.setId(employee.getEmpId());
		return bean;
	}
}