package com.fooddelivery.daoimpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;

import com.entity.Customer;
import com.entity.Employee;
import com.entity.OrderDetails;
import com.entity.Orders;
import com.fooddelivery.dao.OrderDAO;
import com.hibernate.Hibernateutil;

public class OrderDaoimpl implements OrderDAO {

	public boolean saveOrder(Orders order) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			session.save(order);
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

	public List<Orders> findAll() {
		try (Session session = Hibernateutil.getSession()) {
			List<Orders> order = session.createQuery("from Orders", Orders.class).getResultList();
			return order;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public Orders findOrderByID(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Orders order = session.get(Orders.class, id);
			return order;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}
	
	public List<Orders> findorderBycustId(int custId){
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			String sqlQuery = "SELECT * FROM Orders WHERE orderId = :custId ORDER BY orderDate DESC";
		
			List<Orders> orders = session.createNativeQuery(sqlQuery, Orders.class)
					.setParameter("custId", custId).getResultList();

			session.getTransaction().commit();

			return orders;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Orders> findorderByempId(int empId){
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			String sqlQuery = "SELECT * FROM Orders WHERE orderId = :empId ORDER BY orderDate DESC";
		
			List<Orders> orders = session.createNativeQuery(sqlQuery, Orders.class)
					.setParameter("empId", empId).getResultList();

			session.getTransaction().commit();

			return orders;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateOrderById(int orderId, LocalDate orderdate,int amount, String status, Employee employee, Customer customer) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			Orders existorder = session.load(Orders.class, orderId);
			if(existorder != null) {
			// update existing details with the new one
			existorder.setorderId(orderId);
			existorder.setorderDate(orderdate);
			existorder.setamount(amount);
			existorder.setstatus(status);
			existorder.setCustomer(customer);
			existorder.setEmployee(employee);
			session.update(existorder);
			session.getTransaction().commit();
			System.out.println("order with ID " + orderId + " updated successfully.");
			return true;
			} else {
			System.out.println("orderId with ID " + orderId + " does not exist.");
		}
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;
	}

	public boolean removeOrderById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Orders order = session.get(Orders.class, id);
			session.beginTransaction();
			if (order != null) {
				order.setStatus('I');
				session.saveOrUpdate(order);
			} else {
				System.out.println("FoodCategory details not found!");
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

}
