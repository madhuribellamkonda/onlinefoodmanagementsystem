package Testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Customer;
import com.hibernate.Hibernateutil;
import com.service.CustomerService;
import com.serviceimpl.CustomerServiceimpl;

public class TestCustomerService {
	// static SessionFactory sessionFactory;
	static CustomerService customerService;

	@BeforeClass
	public static void setUp() {
		// Session session = Hibernateutil.getSession();
		customerService = new CustomerServiceimpl();
	}

	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer(4, "Shyam", "Hyderabad", "8224345345");
		assertTrue(customerService.saveCustomer(customer));
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = customerService.findCustomerById(1);
		assertNotNull(customer);
		assertTrue(customer.getCustId() == 1);
	}

	@Test
	public void testGetAllCustomers() {
		List<Customer> customer = customerService.findAll();
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		boolean customer = customerService.updateCustomer(1, "Raghu", "Chenai", "8367463820");
		assertTrue(customer);
	}

	@Test
	public void testDeletecustomer() {
		boolean customer = customerService.removeCustomerById(1);
		assertTrue(customer);
	}

	@AfterClass
	public static void tearDown() {
		// sessionFactory.close();
	}

}
