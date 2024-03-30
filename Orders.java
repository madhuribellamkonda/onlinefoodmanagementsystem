package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	private int orderId;

	private LocalDate orderDate;

	private int amount;

	private String statusOfOrder;

	@Column(columnDefinition = "CHAR DEFAULT 'A'")
	char status;
	
	public int amout() {
		OrderDetails orderdetails = new OrderDetails();
		FoodProduct foodproduct = new FoodProduct();
		amount = orderdetails.getquantity()* foodproduct.getprice();
		return amount;
	}

	@ManyToOne
	private Employee employee;

	@ManyToOne
	private Customer customer;

	public Orders() {
	}

	public Orders(int orderId, LocalDate orderDate,  String statusOfOrder, char status, Employee employee,
			Customer customer) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.statusOfOrder = "Pending";
		this.status = status;
		this.employee = employee;
		this.customer = customer;
	}

	public Orders(int orderId, LocalDate orderDate, int amount, String statusOfOrder) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.statusOfOrder = "Pending";
	}

	

	public Orders(int orderId, LocalDate orderDate, int amount, String statusOfOrder, char status, Employee employee,
			Customer customer) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.statusOfOrder = "Pending";
		this.status = status;
		this.employee = employee;
		this.customer = customer;
	}

	public int getorderId() {
		return orderId;
	}

	public void setorderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getorderDate() {
		return orderDate;
	}

	public void setorderDate(LocalDate date) {
		this.orderDate = date;
	}

	public int getamount() {
		return amount;
	}

	public void setamount(int amount) {
		this.amount = amount;
	}

	public String getstatus() {
		return statusOfOrder;
	}

	public void setstatus(String status) {
		this.statusOfOrder = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", amount=" + amount + ", statusOfOrder="
				+ statusOfOrder + ", status=" + status + ", employee=" + employee + ", customer=" + customer + "]";
	}

}
