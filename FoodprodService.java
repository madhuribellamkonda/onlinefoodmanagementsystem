package com.service;

import java.util.List;

import com.entity.FoodCategory;
import com.entity.FoodProduct;
import com.entity.OrderDetails;

public interface FoodprodService {
	boolean saveFoodproduct(FoodProduct prod);

	List<FoodProduct> findAll();

	FoodProduct findFoodProductById(int id);
	
	List<FoodProduct> getFoodProductsBycategoryId(int categoryId);

	boolean updateFoodProductById(int foodProductId, String productName, int price, FoodCategory foodCategory);

	boolean removeFoodprodById(int id);
}
