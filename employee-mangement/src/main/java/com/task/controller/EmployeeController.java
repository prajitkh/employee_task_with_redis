package com.task.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.configuration.HMACUtil;
import com.task.configuration.Response;
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
	public ResponseEntity<?> AddEmployee(@RequestHeader("HMAC") String receivedHmac,
			@RequestBody Map<String, Object> requestData) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();

		String productJson = objectMapper.writeValueAsString(requestData);

		try

		{

			if (HMACUtil.verifyHMAC(productJson, receivedHmac)) {

				Map<String, Object> requestData1 = objectMapper.readValue(productJson,
						new TypeReference<Map<String, Object>>() {
						});

				EmployeeDto dto = objectMapper.convertValue(requestData1, EmployeeDto.class);
				employeeService.AddEmployee(dto);

				return new ResponseEntity<>(new Response("Request is valid,Employee added Successfully"),
						HttpStatus.OK);

			} else {

				return new ResponseEntity<>(new Response("Request is invalid or has been modified"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

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

	@PostMapping("/generate-hmac")
	public String generateHMAC(@RequestBody Map<String, Object> requestData) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(requestData);
		return HMACUtil.generateHMAC(jsonData);
	}
}
