package com.fooddelivery.daoimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.entity.Customer;
import com.entity.Employee;
import com.fooddelivery.dao.EmployeeDAO;
import com.hibernate.*;

public class EmployeeDaoImpl implements EmployeeDAO {

	public boolean saveEmpolyee(Employee employee) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
			session.clear();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Employee> findAll() {
		try (Session session = Hibernateutil.getSession()) {
			List<Employee> employee = session.createQuery("from Employee", Employee.class).getResultList();
			return employee;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public Employee FindEmpByID(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Employee employee = session.get(Employee.class, id);
			return employee;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}
	
	

	public boolean updateEmployee(int empId, String name, LocalDate dateOfJoined, float salary, String mobile) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			Employee existemp = session.load(Employee.class, empId);
			if (existemp != null) { // update existing details with the new one
			existemp.setempId(empId);
			existemp.setname(name);
			existemp.setdateOfJoined(dateOfJoined);
			existemp.setsalary(salary);
			existemp.setmobile(mobile);
			
			session.update(existemp);
			session.getTransaction().commit();
			System.out.println("Employee with ID " + empId + " updated successfully.");
			return true;
			}else {
			System.out.println("Employee with ID " + empId + " does not exist.");
		}
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;
	}


	public boolean removeEmployeeById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Employee emp = session.get(Employee.class, id);
			session.beginTransaction();
			if (emp != null) {
				emp.setStatus('I');
				session.saveOrUpdate(emp);
			} else {
				System.out.println("Employee details not found!");
			}
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;
	}

	@Override
	public List<Employee> findemployeeByorderID(int orderId) {
		try (Session session = Hibernateutil.getSession()) {

			String sqlQuery = "SELECT * FROM onlinefooddelivery.employee e, onlinefooddelivery.orders o where o.orderId= :orderId";

			List<Employee> employee = session.createNativeQuery(sqlQuery, Employee.class)
					.setParameter("orderId", orderId).getResultList();
			return employee;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}
}
