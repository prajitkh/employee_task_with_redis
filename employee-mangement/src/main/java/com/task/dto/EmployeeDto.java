package com.task.dto;

import java.io.Serializable;

public class EmployeeDto implements Serializable {

	private String name;

	private String department;

	private Double salary;

	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public EmployeeDto() {
	}

	public EmployeeDto(String name, String department, Double salary, Integer age) {
		super();
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.age = age;
	}

	@Override
	public String toString() {
		return "EmployeeDto [name=" + name + ", department=" + department + ", salary=" + salary + ", age=" + age + "]";
	}

}
