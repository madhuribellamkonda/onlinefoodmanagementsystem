package com.fooddelivery;

import java.util.Scanner;

import com.entity.Customer;

public class MainOperations {

	static Scanner scanner = new Scanner(System.in);

	public static void mainOps() {
		while (true) {
			System.out.println("Welcome to Online FoodDelivery System:\n " + "1.Customer Details\n"
					+ "2.Employee Details\n" + "3.FoodCategory Details\n" + "4.Foodproduct Details\n" + "5.Orders\n"
					+ "6.OrdersDetails\n" + "7.Exit\nEnter your choice");
			int input = scanner.nextInt();

			switch (input) {
			case 1:
				AllOperations.CustomerOperations();
				System.out.println("******************************");
				break;

			case 2:
				AllOperations.EmployeeOperations();
				System.out.println("******************************");
				break;
			case 3:
				AllOperations.FoodCategoryOperations();
				System.out.println("******************************");
				break;
			case 4:
				AllOperations.FoodProductOperations();
				System.out.println("******************************");
				break;
			case 5:
				AllOperations.OrdersOperations();
				System.out.println("******************************");
				break;
			case 6:
				AllOperations.OrderDetailsOperations();
				System.out.println("******************************");
				break;

			}

		}
	}

	public static void main(String args[]) {
		mainOps();
	}
}
