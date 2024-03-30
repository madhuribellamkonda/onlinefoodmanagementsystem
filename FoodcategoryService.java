package com.service;

import java.util.List;

import com.entity.FoodCategory;
import com.entity.FoodProduct;

public interface FoodcategoryService {
	boolean saveFoodCategory(FoodCategory category);

	List<FoodCategory> findAll();

	FoodCategory findFoodCategoryById(int id);

	boolean removeFoodCategoryById(int id);

	boolean updateFoodCategory(int id, String FoodCategory);

}
