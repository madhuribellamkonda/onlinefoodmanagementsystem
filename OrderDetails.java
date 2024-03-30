package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {
	@Id
	private int orderDetailsId;

	private int quantity;

	@Column(columnDefinition = "CHAR DEFAULT 'A'")
	char status;

	@ManyToOne
	Orders orders;

	@OneToOne
	FoodProduct foodProduct;

	public OrderDetails() {
	}

	public OrderDetails(int orderDetailsId, int quantity, char status) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.quantity = quantity;
		this.status = status;
	}

	public OrderDetails(int orderDetailsId, int quantity, Orders order, FoodProduct foodProduct) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.quantity = quantity;
		this.orders = order;
		this.foodProduct = foodProduct;
	}

	public OrderDetails(int orderDetailsId, int quantity, char status, Orders orders, FoodProduct foodProduct) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.quantity = quantity;
		this.status = status;
		this.orders = orders;
		this.foodProduct = foodProduct;
	}

	public OrderDetails(int orderDetailsId, int quantity) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.quantity = quantity;
	}

	public int getorderDetailsId() {
		return orderDetailsId;
	}

	public void setorderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public FoodProduct getFoodProduct() {
		return foodProduct;
	}

	public void setFoodProduct(FoodProduct foodProduct) {
		this.foodProduct = foodProduct;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailsId=" + orderDetailsId + ", quantity=" + quantity + ", status=" + status
				+ ", orders=" + orders + ", foodProduct=" + foodProduct + "]";
	}

}
