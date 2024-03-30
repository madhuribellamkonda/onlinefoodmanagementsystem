package com.service;

import java.time.LocalDate;
import java.util.List;

import com.entity.Employee;

public interface EmployeeService {
	boolean saveEmpolyee(Employee employee);

	List<Employee> findAll();

	Employee FindEmpByID(int id);
	
	List<Employee> findemployeeByorderID(int orderId);

	boolean updateEmployee(int empId, String name, LocalDate dateOfJoined, float salary, String mobile);

	boolean removeEmployeeById(int id);
}
