package com.khutzi.employees.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("com.khutzi.employees");
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}