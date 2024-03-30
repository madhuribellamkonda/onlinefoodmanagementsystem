package Testing;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Employee;
import com.hibernate.Hibernateutil;
import com.service.EmployeeService;
import com.serviceimpl.EmployeeServiceimpl;

public class TestEmployeeService {

	//static SessionFactory sessionFactory;
	static EmployeeService employeerService;

	@BeforeClass
	public static void setUp() {
		//Session session = Hibernateutil.getSession();
		employeerService = new EmployeeServiceimpl();

	}
	
	
	
	  @Test public void testSaveemployee() { Employee employee = new
	  Employee(3,"Raghu", LocalDate.parse("2024-03-13"), 20000, "9875676768",'A');
	  assertTrue(employeerService.saveEmpolyee(employee)); }
	 

	
	  @Test public void testGetemployeeById() { Employee employee =
	  employeerService.FindEmpByID(2); assertNotNull(employee);
	  assertTrue(employee.getempId()== 2); }
	  
	 
	  
	  @Test public void testGetAllemployees() { List<Employee> employee =
	  employeerService.findAll(); assertNotNull(employee); }
	  
	  
	  
	  
	  @Test public void testUpdateEmployee() { boolean employee =
	  employeerService.updateEmployee(2, "Hema",
	 LocalDate.parse("2024-03-14"),24700, "845647421"); assertTrue(employee); }
	 
	 @Test public void testDeleteemployee() { boolean employee =
	  employeerService.removeEmployeeById(1); assertTrue(employee); }
	 
	@AfterClass
	public static void tearDown() {
		//sessionFactory.close();
	}


}
