package com.service;

import java.time.LocalDate;
import java.util.List;

import com.entity.Customer;
import com.entity.Employee;
import com.entity.Orders;

public interface OrderService {
	boolean saveOrder(Orders order);

	List<Orders> findAll();

	Orders findOrderByID(int id);

	List<Orders> findorderByempId(int empId);

	List<Orders> findorderBycustId(int custId);

	boolean updateOrderById(int orderId, LocalDate orderdate, int amount, String status, Employee employee,
			Customer customer);

	boolean removeOrderById(int id);
}
