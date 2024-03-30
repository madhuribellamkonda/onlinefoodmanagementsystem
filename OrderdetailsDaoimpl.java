package com.fooddelivery.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.entity.Orders;
import com.entity.FoodProduct;
import com.entity.OrderDetails;
import com.fooddelivery.dao.OrderDetailsDAO;
import com.hibernate.Hibernateutil;

public class OrderdetailsDaoimpl implements OrderDetailsDAO {

	public boolean saveOderdetails(OrderDetails Orerdetails) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			session.save(Orerdetails);
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

	public List<OrderDetails> findAll() {
		try (Session session = Hibernateutil.getSession()) {
			List<OrderDetails> orerdetails = session.createQuery("from OrderDetails", OrderDetails.class)
					.getResultList();
			return orerdetails;

		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public OrderDetails findeorderDetailsById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			OrderDetails orerdetails = session.get(OrderDetails.class, id);
			return orerdetails;

		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return null;
	}

	public List<OrderDetails> findorderDetailsByordersId(int orderId) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			String sqlQuery = "SELECT * FROM orderDetails WHERE OrderDetailsId = :orderId ";

			List<OrderDetails> orderDetails = session.createNativeQuery(sqlQuery, OrderDetails.class)
					.setParameter("orderId", orderId).getResultList();

			session.getTransaction().commit();

			return orderDetails;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean updateOrderDetailsById(int orderDetailsId, int quantity, Orders order, FoodProduct foodProduct) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			OrderDetails existOrderDetails = session.load(OrderDetails.class, orderDetailsId);
			if (existOrderDetails != null) {
				// update existing details with the new one
				existOrderDetails.setorderDetailsId(orderDetailsId);
				existOrderDetails.setquantity(quantity);
				existOrderDetails.setOrders(order);
				existOrderDetails.setFoodProduct(foodProduct);
				session.update(existOrderDetails);
				session.getTransaction().commit();
				System.out.println("OrderDetailsId with ID " + orderDetailsId + " updated successfully.");
				return true;
			} else {
				System.out.println("OrderDetailsId with ID " + orderDetailsId + " does not exist.");
			}

		} catch (HibernateException e) {
			System.out.println("Hibernate exception is: " + e);
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
		return false;

	}

	public boolean removeOrderDetailsById(int id) {
		try (Session session = Hibernateutil.getSession()) {
			OrderDetails orderdetails = session.get(OrderDetails.class, id);
			session.beginTransaction();
			if (orderdetails != null) {
				orderdetails.setStatus('I');
				session.saveOrUpdate(orderdetails);
			} else {
				System.out.println("FoodCategory details not found!");
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

	@Override
	public List<OrderDetails> findorderDetailsByfoodProductsId(int foodProductId) {
		try (Session session = Hibernateutil.getSession()) {
			session.beginTransaction();
			String sqlQuery = "SELECT * FROM OrderDetails WHERE OrderDetailsId = :foodProductId ";

			List<OrderDetails> orderDetails = session.createNativeQuery(sqlQuery, OrderDetails.class)
					.setParameter("foodProductId", foodProductId).getResultList();

			session.getTransaction().commit();

			return orderDetails;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
