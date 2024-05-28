package com.task.entity.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.dto.EmployeeDto;
import com.task.dto.IListEmployeeDto;
import com.task.entity.Employee;
import com.task.entity.repository.EmployeeReposiotry;
import com.task.entity.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final String EMPLOYEE_LIST_KEY = "employeeList";

	@Autowired
	private EmployeeReposiotry employeeReposiotry;

	@Override
	public EmployeeDto AddEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setAge(employeeDto.getAge());
		employee.setName(employeeDto.getName());
		employee.setSalary(employeeDto.getSalary());
		employee.setDepartment(employeeDto.getDepartment());
		employeeReposiotry.save(employee);

		ObjectMapper mapper = new ObjectMapper();

		try {
			String jsonString = mapper.writeValueAsString(employee);
			redisTemplate.opsForList().rightPush(EMPLOYEE_LIST_KEY, jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}

		return employeeDto;

	}

	@Override
	public Page<IListEmployeeDto> getAllEmployee(String search, int pageNo, int pageSize, String sortField,
			String sortDirection) throws JsonMappingException, JsonProcessingException {

		if (redisTemplate.hasKey(EMPLOYEE_LIST_KEY)) {

			List<Object> range = redisTemplate.opsForList().range(EMPLOYEE_LIST_KEY, 0, -1);
			List<IListEmployeeDto> employeeList = new LinkedList<>();

			ObjectMapper mapper = new ObjectMapper();

			for (Object obj : range) {
				if (obj instanceof String) {
					String data = (String) obj;

					try {

						TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
						};
						Map<String, Object> map = mapper.readValue(data, typeReference);

						IListEmployeeDto employeeDto = new IListEmployeeDto() {
							@Override
							public Double getSalary() {
								return (Double) map.get("salary");
							}

							@Override
							public String getName() {
								return (String) map.get("name");
							}

							@Override
							public Integer getId() {
								return (Integer) map.get("id");
							}

							@Override
							public String getDepartment() {
								return (String) map.get("department");
							}

							@Override
							public Integer getAge() {
								return (Integer) map.get("age");
							}
						};
						employeeList.add(employeeDto);
					} catch (JsonProcessingException e) {
						e.printStackTrace();

					}
				}
			}

			if (search != null && !search.isEmpty()) {
				employeeList = employeeList.stream().filter(employee -> employeeMatchesSearch(employee, search))
						.collect(Collectors.toList());
			}

			// Paginate the results
			int total = employeeList.size();
			int start = Math.min((pageNo - 1) * pageSize, total);
			int end = Math.min(start + pageSize, total);
			List<IListEmployeeDto> paginatedList = employeeList.subList(start, end);

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return new PageImpl<>(paginatedList, pageable, total);

		} else {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
					sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
							: Sort.by(sortField).descending());
			Page<IListEmployeeDto> allEmployee = employeeReposiotry.getAllEmployee(search, pageable);

			CacheEmployee(allEmployee.getContent());
			return allEmployee;
		}
	}

	private void CacheEmployee(List<IListEmployeeDto> list) {

		ObjectMapper mapper = new ObjectMapper();

		for (IListEmployeeDto employee : list) {
			try {
				String jsonString = mapper.writeValueAsString(employee);
				redisTemplate.opsForList().rightPush(EMPLOYEE_LIST_KEY, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();

			}

		}

	}

	private boolean employeeMatchesSearch(IListEmployeeDto employee, String search) {

		return employee.getName().toLowerCase().contains(search.toLowerCase())
				|| employee.getDepartment().toLowerCase().contains(search.toLowerCase());
	}

	public List<Employee> getAllEmployee() {
		List<Employee> findAll = this.employeeReposiotry.findAll();
		return findAll;
	}
}
