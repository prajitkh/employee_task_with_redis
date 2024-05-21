package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.EmployeeDto;
import com.task.dto.EmployeePaginationResponse;
import com.task.dto.IListEmployeeDto;
import com.task.dto.ResponseDto;
import com.task.entity.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("addUser")
	public ResponseEntity<?> AddEmployee(@RequestBody EmployeeDto employeeDto) {

		try {

			employeeService.AddEmployee(employeeDto);

			return new ResponseEntity<>("user added Successfully", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllEmployee(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection) {

		try {

			Page<IListEmployeeDto> allEmployee = employeeService.getAllEmployee(search, pageNo, pageSize, sortField,
					sortDirection);

			ResponseDto dto = new ResponseDto();
			dto.setData(allEmployee.getContent());
			dto.setPaginationResponse(new EmployeePaginationResponse(allEmployee.getSize(), allEmployee.getNumber() + 1,
					allEmployee.getTotalElements()));

			return new ResponseEntity<>(dto, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
