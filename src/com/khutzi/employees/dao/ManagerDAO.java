package com.khutzi.employees.dao;

import com.khutzi.employees.entity.Manager;

public interface ManagerDAO {

	public void createManager(Manager manager);

	public Manager getEmployeeManager(int id);
}
