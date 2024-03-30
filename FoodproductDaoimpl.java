package com.fooddelivery.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.entity.FoodCategory;
import com.entity.FoodProduct;
import com.entity.Orders;
import com.fooddelivery.dao.FoodProductDAO;
import com.hibernate.Hibernateutil;

public class FoodproductDaoimpl implements FoodProductDAO {

	public boolean saveFoodproduct(FoodProduct prod) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			session.save(prod);
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

	public List<FoodProduct> findAll() {
		List<FoodProduct> foodproduct = null;
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			 foodproduct = session.createQuery("from FoodProduct", FoodProduct.class).getResultList();
			session.getTransaction().commit();
			System.out.println("Foodproduct detailes retrieved successfully");
			return foodproduct;
			
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public FoodProduct findFoodProductById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			FoodProduct foodproduct = session.get(FoodProduct.class, id);
			return foodproduct;
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public List<FoodProduct> getFoodProductsBycategoryId(int categoryId) {

		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();

			// Create a native SQL query
			String sqlQuery = "SELECT * FROM FoodProduct WHERE foodProductId = :foodCategoryId ";

			List<FoodProduct> foodProducts = session.createNativeQuery(sqlQuery, FoodProduct.class)
					.setParameter("foodCategoryId", categoryId).getResultList();

			session.getTransaction().commit();

			return foodProducts;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean updateFoodProductById(int foodProductId, String productName, int price, FoodCategory foodCategory) {
		FoodProduct existFoodproduct = null;
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			existFoodproduct = session.load(FoodProduct.class, foodProductId);
			//
			if (existFoodproduct != null) {
				existFoodproduct.setfoodProductId(foodProductId);
				existFoodproduct.setproductName(productName);
				existFoodproduct.setprice(price);

				existFoodproduct.setFoodCategory(foodCategory);

		
				session.update(existFoodproduct);
				session.getTransaction().commit();
				System.out.println("ProductId with ID " + foodProductId + " updated successfully.");
				return true;
			} else {
				System.out.println("ProductId with ID " + foodProductId + " does not exist.");
			}
		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;
	}

	public boolean removeFoodprodById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			FoodProduct product = session.get(FoodProduct.class, id);
			session.beginTransaction();
			if (product != null) {
				product.setStatus('I');
				session.saveOrUpdate(product);
			} else {
				System.out.println("existFoodproduct details not found!");
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
