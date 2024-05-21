package com.task.dto;

import java.io.Serializable;

public interface IListEmployeeDto extends Serializable {
	public String getDepartment();

	public String getName();

	public Double getSalary();

	public Integer getAge();

	public Integer getId();
}
