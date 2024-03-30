package Testing;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Customer;
import com.entity.Employee;
import com.entity.Orders;
import com.hibernate.Hibernateutil;
import com.service.CustomerService;
import com.service.EmployeeService;
import com.service.OrderService;
import com.serviceimpl.CustomerServiceimpl;
import com.serviceimpl.EmployeeServiceimpl;
import com.serviceimpl.OrderServiceimpl;

public class TestOrdersService {
	static OrderService orderService;
	static CustomerService customerService;
	static EmployeeService employeeService;

	@BeforeClass
	public static void setUp() {
		// Session session = Hibernateutil.getSession();
		orderService = new OrderServiceimpl();
		customerService = new CustomerServiceimpl();
		employeeService = new EmployeeServiceimpl();
	}

	@Test
	public void testSaveOrders() {
		Customer customer = customerService.findCustomerById(1);
		Employee employee = employeeService.FindEmpByID(1);
		Orders orders = new Orders(1, LocalDate.parse("2024-03-12"), 300, "Pending", 'A', employee, customer);
		assertTrue(orderService.saveOrder(orders));
	}

	@Test
	public void testGetordersById() {
		Orders orders = orderService.findOrderByID(1);
		assertNotNull(orders);
		assertTrue(orders.getorderId() == 1);
	}

	@Test
	public void testGetAllorders() {
		List<Orders> orders = orderService.findAll();
		assertNotNull(orders);
	}

	@Test
	public void testUpdateorders() {
		Customer customer = customerService.findCustomerById(1);
		Employee employee = employeeService.FindEmpByID(1);
		boolean orders = orderService.updateOrderById(1, LocalDate.parse("2024-02-13"), 200, "Pending", employee,
				customer);
		assertTrue(orders);
	}

	@Test
	public void testDeleteOrders() {
		boolean orders = orderService.removeOrderById(1);
		assertTrue(orders);
	}

	@AfterClass
	public static void tearDown() {
		// sessionFactory.close();
	}
}
