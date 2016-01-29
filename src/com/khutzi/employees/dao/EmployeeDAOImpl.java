package com.khutzi.employees.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.khutzi.employees.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void createEmployee(Employee employee) {
		EntityManager entityManager = JPAUtils.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}

	@Override
	public List<Employee> getAllEmployees() {
		EntityManager entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		List<Employee> list = null;
		try {
			Query query = entityManager.createQuery("SELECT e FROM Employee e",
					Employee.class);
			list = (List<Employee>) query.getResultList();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			System.out.println("" + e.getClass());
		}
		return list;
	}

	@Override
	public void updateEmployee(int empId, String empName, double salary,
			String depName) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery(
				"SELECT e from Employee e where e.emp_id=" + empId,
				Employee.class);
		List<Employee> employeeList = query.getResultList();
		Employee employee = employeeList.get(0);
		employee.setName(empName);
		employee.setSalary(salary);
		employee.setDepartmentName(depName);
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}

	@Override
	public void deleteEmployee(int empId) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		Employee employee = entityManager.find(Employee.class, empId);
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}

}
