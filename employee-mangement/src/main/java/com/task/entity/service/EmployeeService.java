package com.task.entity.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.task.dto.EmployeeDto;
import com.task.dto.IListEmployeeDto;

@Service
public interface EmployeeService {

	EmployeeDto AddEmployee(EmployeeDto employeeDto);

	Page<IListEmployeeDto> getAllEmployee(String search, int pageNo, int pageSize, String sortField,
			String sortDirection) throws Exception;

}
