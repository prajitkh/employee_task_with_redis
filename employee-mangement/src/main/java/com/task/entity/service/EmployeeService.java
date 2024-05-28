package com.task.entity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.task.dto.EmployeeDto;
import com.task.dto.IListEmployeeDto;
import com.task.entity.Employee;

@Service
public interface EmployeeService {

	EmployeeDto AddEmployee(EmployeeDto employeeDto);

	Page<IListEmployeeDto> getAllEmployee(String search, int pageNo, int pageSize, String sortField,
			String sortDirection) throws Exception;

	public List<Employee> getAllEmployee();
}
