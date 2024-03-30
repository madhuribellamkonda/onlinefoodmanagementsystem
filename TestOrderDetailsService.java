package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.FoodProduct;
import com.entity.OrderDetails;
import com.entity.Orders;
import com.service.FoodprodService;
import com.service.OrderService;
import com.service.OrderdetailsService;
import com.serviceimpl.FoodprodServiceimpl;
import com.serviceimpl.OrderDetailsServiceimpl;
import com.serviceimpl.OrderServiceimpl;

public class TestOrderDetailsService {

	static OrderdetailsService orderdetailsService;
	static FoodprodService foodprodService;
	static OrderService orderService;

	@BeforeClass
	public static void setUp() {
		orderService = new OrderServiceimpl();
		foodprodService = new FoodprodServiceimpl();
		orderdetailsService = new OrderDetailsServiceimpl();
	}

	@Test
	public void testSaveOrderdetails() {
		Orders order = orderService.findOrderByID(1);
		FoodProduct foodproduct = foodprodService.findFoodProductById(1);
		OrderDetails orderDetails = new OrderDetails(2, 1, order, foodproduct);
		assertTrue(orderdetailsService.saveOderdetails(orderDetails));
	}

	@Test
	public void testGetorderdetailsById() {
		OrderDetails orderDetails = orderdetailsService.findeorderDetailsById(1);
		assertNotNull(orderDetails);
		assertTrue(orderDetails.getorderDetailsId() == 1);
	}

	@Test
	public void testGetAllorderdetails() {
		List<OrderDetails> orderDetails = orderdetailsService.findAll();
		assertNotNull(orderDetails);
	}

	@Test
	public void testUpdateOrderdetails() {
		Orders orders = orderService.findOrderByID(1);
		FoodProduct foodProduct = foodprodService.findFoodProductById(1);
		boolean orderdetails = orderdetailsService.updateOrderDetailsById(1, 2, orders, foodProduct);
		assertTrue(orderdetails);
	}

	@Test
	public void testDeleteorderdetails() {
		boolean orderdetails = orderdetailsService.removeOrderDetailsById(1);
		assertTrue(orderdetails);
	}

	@AfterClass
	public static void tearDown() {
		// sessionFactory.close();
	}
}
