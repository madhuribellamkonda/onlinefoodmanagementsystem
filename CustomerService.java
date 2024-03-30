package com.service;

import java.util.List;

import com.entity.Customer;

public interface CustomerService {
	boolean saveCustomer(Customer customer);

	List<Customer> findAll();
	
	Customer findCustomerById(int id);
	
	List<Customer> findCustomerByorderId(int orderid);

	boolean updateCustomer(int custId, String custName, String address, String mobileNo);

	boolean removeCustomerById(int id);
}
