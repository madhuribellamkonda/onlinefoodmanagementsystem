package com.fooddelivery.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.entity.Customer;
import com.fooddelivery.dao.CustomerDAO;
import com.hibernate.Hibernateutil;

public class CustomerDaoimpl implements CustomerDAO {

	public boolean saveCustomer(Customer customer) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			session.save(customer);
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

	public List<Customer> findAll() {
		try (Session session = Hibernateutil.getSession()) {
			List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
			return customers;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;

	}

	public Customer findCustomerById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Customer customer = session.get(Customer.class, id);
			return customer;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public boolean updateCustomer(int custId, String custName, String address, String mobileNo) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			Customer existCust = session.load(Customer.class, custId);
			if (existCust != null) {
				// update existing details with the new one
				existCust.setCustId(custId);
				existCust.setcustName(custName);
				existCust.setMobileNo(mobileNo);
				existCust.setAddress(address);

				session.update(existCust);
				session.getTransaction().commit();
				System.out.println("Customer with ID " + custId + " updated successfully.");
				return true;
			} else {
				System.out.println("Customer with ID " + custId + " does not exist.");
			}
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;
	}

	public boolean removeCustomerById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			Customer cust = session.get(Customer.class, id);
			session.beginTransaction();
			if (cust != null) {
				cust.setStatus('I');
				session.saveOrUpdate(cust);
				session.getTransaction().commit();
				System.out.println("Deleted successfully customer Id:" + id);
				return true;
			} else {
				System.out.println("Customer details not found!");
			}
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;

	}

	@Override
	public List<Customer> findCustomerByorderId(int orderId) {
		try (Session session = Hibernateutil.getSession()) {
			String sqlQuery = "SELECT * FROM onlinefooddelivery.customer c, onlinefooddelivery.orders o where o.orderId=:orderId ";
			List<Customer> customers = session.createNativeQuery(sqlQuery, Customer.class)
					.setParameter("orderId", orderId).getResultList();
			return customers;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;

	}

}