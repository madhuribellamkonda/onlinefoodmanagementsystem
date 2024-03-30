package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class FoodProduct {
	@Id
	private int foodProductId;

	private String productName;

	private int price;

	@Column(columnDefinition = "CHAR DEFAULT 'A'")
	char status;

	@ManyToOne
	FoodCategory foodCategory;

	public FoodProduct() {
	}

	public FoodProduct(int foodProductId, String productName, int price, char status, FoodCategory foodCategory) {
		super();
		this.foodProductId = foodProductId;
		this.productName = productName;
		this.price = price;
		this.status = status;
		this.foodCategory = foodCategory;
	}

	public FoodProduct(int foodProductId, String productName, int price) {
		super();
		this.foodProductId = foodProductId;
		this.productName = productName;
		this.price = price;
	}

	public FoodProduct(int foodProductId, String productName, int price, FoodCategory foodCategory) {
		super();
		this.foodProductId = foodProductId;
		this.productName = productName;
		this.price = price;
		this.foodCategory = foodCategory;
	}

	public int getfoodProductId() {
		return foodProductId;
	}

	public void setfoodProductId(int foodProductId) {
		this.foodProductId = foodProductId;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductName(String productName) {
		this.productName = productName;
	}

	public int getprice() {
		return price;
	}

	public void setprice(int price) {
		this.price = price;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FoodProduct [foodProductId=" + foodProductId + ", productName=" + productName + ", price=" + price
				+ ", status=" + status + ", foodCategory=" + foodCategory + "]";
	}

}
