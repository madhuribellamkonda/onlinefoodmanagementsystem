package com.fooddelivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder.Case;

import com.entity.Customer;
import com.entity.Employee;
import com.entity.FoodCategory;
import com.entity.FoodProduct;
import com.entity.OrderDetails;
import com.entity.Orders;
import com.service.CustomerService;
import com.service.EmployeeService;
import com.service.FoodcategoryService;
import com.service.FoodprodService;
import com.service.OrderService;
import com.service.OrderdetailsService;
import com.serviceimpl.CustomerServiceimpl;
import com.serviceimpl.EmployeeServiceimpl;
import com.serviceimpl.FoodcategoryServiceimpl;
import com.serviceimpl.FoodprodServiceimpl;
import com.serviceimpl.OrderDetailsServiceimpl;
import com.serviceimpl.OrderServiceimpl;

public class AllOperations {
	static CustomerService customerService = new CustomerServiceimpl();
	static EmployeeService employeeService = new EmployeeServiceimpl();
	static FoodcategoryService foodcategoryService = new FoodcategoryServiceimpl();
	static FoodprodService foodprodService = new FoodprodServiceimpl();
	static OrderdetailsService orderdetailsService = new OrderDetailsServiceimpl();
	static OrderService orderService = new OrderServiceimpl();

	static Scanner scanner = new Scanner(System.in);

	public static <T> T getEntityById(Scanner scanner, String entityType) {
		System.out.print("Enter " + entityType + " ID: ");
		int entityId = scanner.nextInt();
		return null;
	}

	// customer inputs
	public static Customer customerInputs() {
		System.out.println("Enter customer Id : ");
		int custId = scanner.nextInt();

		System.out.println("Enter customer name: ");
		scanner.nextLine();
		String custName = scanner.nextLine();

		System.out.println("Enter address: ");
		String address = scanner.nextLine();

		System.out.println("Enter mobile number: ");
		String mobileNo = scanner.nextLine();

		return new Customer(custId, custName, address, mobileNo, 'A');

	}

//Employee inputs
	public static Employee employeeInputs() {
		System.out.println("Enter employeeId : ");
		int empId = scanner.nextInt();

		System.out.println("Enter employee name: ");
		scanner.nextLine();
		String name = scanner.nextLine();

		// taking input for date
		LocalDate inputDate = dateInput();

		System.out.println("Enter salary: ");
		String input = scanner.nextLine(); // Read the entire line of input
		float salary = Float.parseFloat(input);

		System.out.println("Enter mobile number: ");
		String mobileNo = scanner.nextLine();
		return new Employee(empId, name, inputDate, salary, mobileNo, 'A');

	}


	// Foodcategory inputs
	public static FoodCategory foodcategoryInputs() {
		scanner.nextLine();
		System.out.println("Enter foodCategoryId : ");
		int foodCategoryId = scanner.nextInt();

		System.out.println("Enter Foodcategory name: ");
		scanner.nextLine();
		String categoryName = scanner.nextLine();

		return new FoodCategory(foodCategoryId, categoryName, 'A');
	}

//FoodProduct inputs
	public static FoodProduct foodProductInputs() {
		System.out.println("Enter foodProductId : ");
		int foodProductId = scanner.nextInt();

		System.out.println("Enter product name: ");
		scanner.nextLine();
		String productName = scanner.nextLine();

		System.out.println("Enter product price: ");
		int price = scanner.nextInt();

		System.out.println("Enter category Id: ");
		int categoryId = scanner.nextInt();
		FoodCategory foodCategory = foodcategoryService.findFoodCategoryById(categoryId);

		return new FoodProduct(foodProductId, productName, price, 'A', foodCategory);
	}

	public static LocalDate dateInput() {
		LocalDate date = null;

		do {
			// Prompt the user to enter a date
			System.out.print("Enter date (YYYY-MM-DD): ");
			String input = scanner.nextLine().trim();

			// Check if input is empty
			if (input.isEmpty()) {
				//System.out.println("Please enter a valid date.");
				continue;
			}
			try {
				date = LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
			} catch (Exception e) {
				System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
			}
		} while (date == null);

		return date;
	}

//Orders inputs
	public static Orders ordersInputs() {
		System.out.println("Enter orderId : ");
		int orderId = scanner.nextInt();

		LocalDate inputDate = dateInput();
		
		System.out.println("Enter amount : ");
		int amount = scanner.nextInt();

		System.out.println("Enter customer Id: ");
		int custid = scanner.nextInt();
		Customer customer = customerService.findCustomerById(custid);

		System.out.println("Enter empolyee Id: ");
		int empid = scanner.nextInt();
		Employee employee = employeeService.FindEmpByID(empid);

		return  new Orders(orderId, inputDate, amount, null, 'A', employee, customer);

	}

//	Orderdetails inputs -- cmpltd
	public static OrderDetails orderdetailsInputs() {
		scanner.nextLine();
		System.out.println("Enter orderDetailsId : ");
		int orderDetailsId = scanner.nextInt();

		scanner.nextLine();
		System.out.println("Enter quantity of products : ");
		int quantity = scanner.nextInt();

		System.out.println("Enter order Id: ");
		scanner.nextLine();
		int orderId = scanner.nextInt();
		Orders orders = orderService.findOrderByID(orderId);

		System.out.println("Enter foodProductId: ");
		scanner.nextLine();
		int foodProductId = scanner.nextInt();
		FoodProduct foodProduct = foodprodService.findFoodProductById(foodProductId);

		return new OrderDetails(orderDetailsId, quantity, 'A', orders, foodProduct);
	}

	public static void CustomerOperations() {
		while (true) {
			System.out.println("Choose the option: \n 1. Create new Customer \n 2. Get Customer using customer Id \n "
					+ "3. Get All Customers \n " + " 4.Update the Customer \n "
					+ "5. Delete the Customer \n 6. Go back to main menu");
			int input = scanner.nextInt();

			switch (input) {
			case 1:
				Customer customer = customerInputs();
				boolean savedCustomer = customerService.saveCustomer(customer);
				break;
			case 2:
				System.out.println("Enter the Customer id you want : ");
				int id = scanner.nextInt();
				Customer customers = customerService.findCustomerById(id);
				System.out.println("Customer details with id : " + id + "is " + customers);

				break;
			case 3:
				List<Customer> customers2 = customerService.findAll();
				for (Customer cust : customers2) {
					System.out.println(cust);
				}
				break;
			case 4:
				System.out.println("Enter Customer ID:");
				int customerId = scanner.nextInt();

				System.out.println("Enter new name:");
				scanner.nextLine();
				String customerName = scanner.nextLine();

				System.out.println("Enter new address:");
				String customeraddress = scanner.nextLine();

				System.out.println("Enter new mobile number:");
				String mobileNumber = scanner.nextLine();

				customerService.updateCustomer(customerId, customerName, customeraddress, mobileNumber);
				System.out.println("Customer details updated successfully.");
				break;
			case 5:
				System.out.println("Enter customer ID to delete:");
				int customer_Id = scanner.nextInt();
				scanner.nextLine();

				customerService.removeCustomerById(customer_Id);
				System.out.println("Customer with id : " + customer_Id + "deleted successfully");
				break;
			case 6:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");

			}

		}
	}

	public static void EmployeeOperations() {
		while (true) {
			System.out.println("Choose the option: \n 1. Create new Employee \n 2. Get Employee using Employee Id \n "
					+ "3. Get All Employees \n " + " 4.Update the Employee \n "
					+ "5. Delete the Employee \n 6. Go back to main menu");

			int input = scanner.nextInt();

			switch (input) {
			case 1:
				Employee employee = employeeInputs();
				boolean savedEmployee = employeeService.saveEmpolyee(employee);
				System.out.println("Employee details saved successfully. " + savedEmployee);
				break;
			case 2:
				System.out.println("Enter the Employee id you want : ");
				int id = scanner.nextInt();
				Employee employees = employeeService.FindEmpByID(id);
				System.out.println("Employee details with id : " + id + "is " + employees);
				break;
			case 3:
				List<Employee> employees2 = employeeService.findAll();
				for (Employee emp : employees2) {
					System.out.println(emp);
				}
				break;
			case 4:
				System.out.println("Enter Employee ID:");
				int empId = scanner.nextInt();

				System.out.println("Enter new name:");
				scanner.nextLine();
				String empName = scanner.nextLine();

				LocalDate dateOfJoined = dateInput();

				System.out.println("Enter updated Salary : ");
				float salary = scanner.nextFloat();

				System.out.println("Enter new mobile number:");
				scanner.nextLine();
				String mobileNumberString = scanner.nextLine();

				employeeService.updateEmployee(empId, empName, dateOfJoined, salary, mobileNumberString);
				System.out.println("Employee details updated successfully.");
				break;
			case 5:
				System.out.println("Enter employee ID to delete:");
				int employee_Id = scanner.nextInt();
				scanner.nextLine();
				employeeService.removeEmployeeById(employee_Id);
				System.out.println("Employee with id : " + employee_Id + "deleted successfully");
				break;
			case 6:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");

			}
		}
	}

	public static void FoodCategoryOperations() {
		while (true) {
			System.out.println(
					"Choose the option: \n 1. Create new FoodCategory \n 2. Get FoodCategory using FoodCategory Id \n "
							+ "3. Get All FoodCategorys \n " + " 4.Update the FoodCategory \n "
							+ "5. Delete the FoodCategory \n 6. Go back to main menu");
			int input = scanner.nextInt();

			switch (input) {
			case 1:
				FoodCategory foodCategory = foodcategoryInputs();
				boolean savedFoodCategory = foodcategoryService.saveFoodCategory(foodCategory);
				break;
			case 2:
				System.out.println("Enter the FoodCategory id you want : ");
				int id = scanner.nextInt();
				FoodCategory foodCategorys = foodcategoryService.findFoodCategoryById(id);
				System.out.println("FoodCategory details with id : " + id + "is " + foodCategorys);

				break;
			case 3:
				List<FoodCategory> foodCategories = foodcategoryService.findAll();
				for (FoodCategory category : foodCategories) {
					System.out.println(category);
				}
				break;
			case 4:
				System.out.println("Enter FoodCategory ID:");
				int foodCategoryId = scanner.nextInt();

				System.out.println("Enter FoodCategory name:");
				scanner.nextLine();
				String foodCategoryName = scanner.nextLine();

				foodcategoryService.updateFoodCategory(foodCategoryId, foodCategoryName);
				System.out.println("foodCategory details updated successfully.");
				break;

			case 5:
				System.out.println("Enter FoodCategory ID to delete:");
				int FoodCategory_Id = scanner.nextInt();
				scanner.nextLine();
				;
				foodcategoryService.removeFoodCategoryById(FoodCategory_Id);
				System.out.println("FoodCategory with id:" + FoodCategory_Id + "deleted successfully");
				break;
			case 6:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");

			}

		}
	}

	public static void FoodProductOperations() {
		while (true) {
			System.out.println(
					"Choose the option: \n 1. Create new FoodProduct \n 2. Get FoodProduct using FoodProduct Id \n "
							+ "3. Get All FoodProduct \n  4.Get FoodProduct using FoodCategory Id \n"
							+ "5.Update the FoodProduct \n " + "6. Delete the FoodProduct \n 7. Go back to main menu");

			int input = scanner.nextInt();

			switch (input) {
			case 1:
				FoodProduct foodProduct = foodProductInputs();
				boolean savedfoodProducts = foodprodService.saveFoodproduct(foodProduct);
				System.out.println("FoodProduct details saved successfully. " + savedfoodProducts);
				break;
			case 2:
				System.out.println("Enter the FoodProducts id you want : ");
				int id = scanner.nextInt();
				FoodProduct foodProduct2 = foodprodService.findFoodProductById(id);
				System.out.println("FoodProduct using foodProduct id is :" + foodProduct2);
				break;

			case 3:
				List<FoodProduct> foodProducts = foodprodService.findAll();
				for (FoodProduct product : foodProducts) {
					System.out.println(product);
				}
				break;

			case 4:
				System.out.println("Enter the FoodCategory id you want : ");
				int id1 = scanner.nextInt();
				List<FoodProduct> foodProducts2 = foodprodService.getFoodProductsBycategoryId(id1);
				System.out.println("Foodproduct using category id is : " + foodProducts2);
				break;
			case 5:
				System.out.println("Enter the id you want to update : ");
				int foodPrdoduct_Id = scanner.nextInt();

				System.out.println("Enter the updated  foodproduct name: ");
				String foodproduct_name = scanner.next();

				System.out.println("Enter the price you want to update : ");
				int foodPrdoduct_price = scanner.nextInt();

				System.out.println("Enter the updated category id: ");
				int updated_category_id = scanner.nextInt();
				FoodCategory foodCategory = foodcategoryService.findFoodCategoryById(updated_category_id);
				foodprodService.updateFoodProductById(foodPrdoduct_Id, foodproduct_name, foodPrdoduct_price,
						foodCategory);

				System.out.println("FoodProduct details updated successfully.");

				break;

			case 6:
				System.out.println("Enter the id you want to delete: ");
				int delete_id = scanner.nextInt();
				boolean product = foodprodService.removeFoodprodById(delete_id);
				break;

			case 7:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");
			}
		}
	}

	public static void OrdersOperations() {
		while (true) {
			System.out.println("Choose the option: \n 1.Create new Order \n2.Get orders using order Id \n "
					+ "3. Get All orders \n 4.Get order using customer Id\n5.Get order using employee Id\n"
					+ "6.Update the orders \n "+"7. Delete the orders \n8. Go back to main menu");

			int input = scanner.nextInt();

			switch (input) {
			case 1:
				Orders orders = ordersInputs();
				boolean savedorders = orderService.saveOrder(orders);
				System.out.println("Orders details saved successfully. " + savedorders);
				break;
			case 2:
				System.out.println("Enter the order id you want : ");
				int id = scanner.nextInt();
				Orders order = orderService.findOrderByID(id);
				System.out.println("Order using foodProduct id is :" + order);
				break;

			case 3:
				List<Orders> orders5 = orderService.findAll();
				for (Orders orders1 : orders5) {
					System.out.println(orders1);
				}
				break;

			case 4:
				System.out.println("Enter the customer id you want : ");
				int customerId = scanner.nextInt();
				List<Orders> orders2 = orderService.findorderBycustId(customerId);
				System.out.println("Orders using customer id is : " + orders2);
				break;

			case 5:
				System.out.println("Enter the employee id you want : ");
				int employeeId = scanner.nextInt();
				List<Orders> orders3 = orderService.findorderByempId(employeeId);
				System.out.println("Orders using employee id is : " + orders3);
				break;

			case 6:
				System.out.println("Enter the id you want to update : ");
				int order_Id = scanner.nextInt();

				System.out.println("Enter the updated  statusOfProduct name: ");
				String foodproduct_name = scanner.next();
				
				System.out.println("Enter the amount you want to update : ");
				int order_amount = scanner.nextInt();
				
				System.out.println("Enter the updated customer id: ");
				int updated_cust_id = scanner.nextInt();
				Customer customer = customerService.findCustomerById(updated_cust_id);

				System.out.println("Enter the updated employee id: ");
				int updated_emp_id = scanner.nextInt();
				Employee employee = employeeService.FindEmpByID(updated_emp_id);

				LocalDate orderdDate = dateInput();
				
				orderService.updateOrderById(order_Id, orderdDate, order_amount, foodproduct_name, employee, customer);

				System.out.println("Orders details updated successfully.");

				break;

			case 7:
				System.out.println("Enter the id you want to delete: ");
				int orderId = scanner.nextInt();
				boolean orders4 = orderService.removeOrderById(orderId);
				break;

			case 8:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");
			}
		}
	}

	public static void OrderDetailsOperations() {
		while (true) {

			System.out.println(
					"Choose the option: \n 1.Create new OrderDetails \n 2.Get orderDetails using OrderDetails Id \n "
							+ "3.Get All OrderDetails \n "
							+ "4.Get OrderDetails By Order \n 5.Get OrderDetails By Foodproduct \n 6.Update the OrderDetails \n "
							+ "7.Delete the OrderDetails \n 8.Go back to main menu");

			int input = scanner.nextInt();

			switch (input) {
			case 1:
				OrderDetails orderDetails = orderdetailsInputs();
				boolean savedEntity = orderdetailsService.saveOderdetails(orderDetails);
				System.out.println("OrderDetails  saved successfully. " + savedEntity);
				break;
			case 2:
				System.out.println("Enter the orderDetails id you want : ");
				int id = scanner.nextInt();
				OrderDetails orderdetails = orderdetailsService.findeorderDetailsById(id);
				System.out.println("orderdetails using orderdetails id is :" + orderdetails);
				break;
			case 3:
				List<OrderDetails> orderDetails2 = orderdetailsService.findAll();
				for (OrderDetails details : orderDetails2) {
					System.out.println(details);
				}
				break;
			case 4:
				System.out.println("Enter the order id you want : ");
				int orderId = scanner.nextInt();
				List<OrderDetails> ordersdetails = orderdetailsService.findorderDetailsByordersId(orderId);
				System.out.println("OrderDetails using order id is : " + ordersdetails);
				break;

			case 5:
				System.out.println("Enter the foodProduct id you want : ");
				int productId = scanner.nextInt();
				List<OrderDetails> ordersdetails1 = orderdetailsService.findorderDetailsByfoodProductsId(productId);
				System.out.println("OrderDetails using foodProductId id is : " + ordersdetails1);
				break;

			case 6:
				System.out.println("Enter the id you want to update : ");
				int orderDetails_Id = scanner.nextInt();

				System.out.println("Enter the quantity you want to update : ");
				int quantity = scanner.nextInt();

				System.out.println("Enter the updated order id: ");
				int updated_order_id = scanner.nextInt();
				Orders order = orderService.findOrderByID(updated_order_id);

				System.out.println("Enter the foodProduct id: ");
				int updated_foodProduct_id = scanner.nextInt();
				FoodProduct foodproduct = foodprodService.findFoodProductById(updated_foodProduct_id);
				
				  orderdetailsService.updateOrderDetailsById(orderDetails_Id, quantity, order, foodproduct);

				System.out.println("Ordedetails updated successfully.");

				break;
			case 7:
				System.out.println("Enter the id you want to delete: ");
				int orderdetailsId = scanner.nextInt();
				boolean orderDetails1 = orderdetailsService.removeOrderDetailsById(orderdetailsId);
				break;

			case 8:
				System.out.println("Do you want to go back to the main menu? (yes/no)");
				String choice = scanner.next();
				if (choice.equalsIgnoreCase("yes")) {
					return;
				}
			default:
				System.out.println("Invalid option. Please choose again.");
			}
		}
	}
}
