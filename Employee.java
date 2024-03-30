package com.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee { // delivery person {
	@Id
	private int empId;

	private String name;

	private LocalDate dateOfJoined;

	private float salary;

	private String mobile;

	@Column(columnDefinition = "CHAR DEFAULT 'A'")
	char status;

	public Employee() {
	}

	public Employee(int empId, String name, LocalDate dateOfJoined, float salary, String mobile) {
		super();
		this.empId = empId;
		this.name = name;
		this.dateOfJoined = dateOfJoined;
		this.salary = salary;
		this.mobile = mobile;
	}

	public Employee(int empId, String name, LocalDate dateOfJoined, float salary, String mobile, char status) {
		super();
		this.empId = empId;
		this.name = name;
		this.dateOfJoined = dateOfJoined;
		this.salary = salary;
		this.mobile = mobile;
		this.status = status;
	}

	public int getempId() {
		return empId;
	}

	public void setempId(int empId) {
		this.empId = empId;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public LocalDate getdateOfJoined() {
		return dateOfJoined;
	}

	public void setdateOfJoined(LocalDate dateOfJoined) {
		this.dateOfJoined = dateOfJoined;
	}

	public float getsalary() {
		return salary;
	}

	public void setsalary(float salary) {
		this.salary = salary;
	}

	public String getmobile() {
		return mobile;
	}

	public void setmobile(String mobile) {
		this.mobile = mobile;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", dateOfJoined=" + dateOfJoined + ", salary=" + salary
				+ ", mobile=" + mobile + ", status=" + status + "]";
	}

}
