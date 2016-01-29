package com.khutzi.employees.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.RequestWrapper;

import com.khutzi.employees.dao.EmployeeDAO;
import com.khutzi.employees.dao.EmployeeDAOImpl;
import com.khutzi.employees.dao.ManagerDAO;
import com.khutzi.employees.dao.ManagerDAOImpl;
import com.khutzi.employees.entity.Employee;
import com.khutzi.employees.entity.Manager;

@Path("/employees")
@Produces("application/json")
public class EmployeeRestService {

	EmployeeDAO employeeDAO;

	ManagerDAO managerDAO;

	@GET
	@Path("/{name}/{salary}/{depName}/{managerId}/{managerName}")
	@Produces("application/json")
	public Response createEmployee(@PathParam("name") String name,
			@PathParam("salary") double salary,
			@PathParam("depName") String depName,
			@PathParam("managerId") int managerId,
			@PathParam("managerName") String managerName) {
		Employee employee = new Employee();
		employeeDAO = new EmployeeDAOImpl();
		managerDAO = new ManagerDAOImpl();

		Manager manager = managerDAO.getEmployeeManager(managerId);

		if (manager == null) {
			Manager manager2 = new Manager();
			manager2.setManager_id(managerId);
			manager2.setName(managerName);
			managerDAO.createManager(manager2);

			employee.setName(name);
			employee.setSalary(salary);
			employee.setDepartmentName(depName);
			employee.setManager(manager2);

			employeeDAO.createEmployee(employee);
		} else {
			employee.setName(name);
			employee.setSalary(salary);
			employee.setDepartmentName(depName);
			employee.setManager(manager);
			employeeDAO.createEmployee(employee);
		}

		String response = "{\n"
				+ "  \"message\": \"employee is added successfully\"\n " + "}";
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/listEmployees")
	public Response getEmployees() {
		employeeDAO = new EmployeeDAOImpl();
		List<Employee> list = employeeDAO.getAllEmployees();
		if (list == null) {
			return Response.status(200).entity(new ArrayList<Employee>())
					.build();
		}
		return Response.status(200).entity(employeeDAO.getAllEmployees())
				.build();
	}

	@GET
	@Path("/updateEmployee/{empId}/{empName}/{salary}/{depName}")
	public Response updateEmployee(@PathParam("empId") int empId,
			@PathParam("empName") String empName,
			@PathParam("salary") double salary,
			@PathParam("depName") String depName) {
		employeeDAO = new EmployeeDAOImpl();
		employeeDAO.updateEmployee(empId, empName, salary, depName);
		return Response.status(200).entity("updated successfully").build();
	}

	@GET
	@Path("/deleteEmployee/{id}")
	public Response deleteEmployee(@PathParam("id") int id) {
		employeeDAO = new EmployeeDAOImpl();
		employeeDAO.deleteEmployee(id);
		String response = "{\n"
				+ " \"message\":  \"Employee is deleted successfully\"\n "
				+ "}";
		return Response.status(200).entity(response).build();
	}
}