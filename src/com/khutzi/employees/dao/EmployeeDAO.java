package com.khutzi.employees.dao;

import java.util.List;

import com.khutzi.employees.entity.Employee;

public interface EmployeeDAO {

	public void createEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void updateEmployee(int empId, String empName, double salary,
			String depName);

	public void deleteEmployee(int empId);
}
