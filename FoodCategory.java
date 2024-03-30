package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FoodCategory {

	@Id
	private int foodCategoryId;

	private String categoryName;

	@Column(columnDefinition = "CHAR DEFAULT 'A'")
	char status;

	public FoodCategory() {
	}

	public FoodCategory(int foodCategoryId, String categoryName, char status) {
		super();
		this.foodCategoryId = foodCategoryId;
		this.categoryName = categoryName;
		this.status = status;
	}

	public FoodCategory(int foodCategoryId, String categoryName) {
		super();
		this.foodCategoryId = foodCategoryId;
		this.categoryName = categoryName;
	}

	public int getfoodCategoryId() {
		return foodCategoryId;
	}

	public void setfoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}

	public String getcategoryName() {
		return categoryName;
	}

	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FoodCategory [foodCategoryId=" + foodCategoryId + ", categoryName=" + categoryName + ", status="
				+ status + "]";
	}

	
}
