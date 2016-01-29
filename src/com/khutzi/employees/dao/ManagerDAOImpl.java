package com.khutzi.employees.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.khutzi.employees.entity.Employee;
import com.khutzi.employees.entity.Manager;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public void createManager(Manager manager) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(manager);
		entityManager.getTransaction().commit();
		entityManager.clear();
		entityManager.close();
	}

	@Override
	public Manager getEmployeeManager(int id) {
		EntityManager entityManager = JPAUtils.getEntityManager();
		entityManager.getTransaction().begin();
		Manager manager = entityManager.find(Manager.class, id);
		entityManager.clear();
		entityManager.close();
		if (manager == null) {
			return null;
		}
		return manager;

	}
}
