package com.task.entity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.dto.IListEmployeeDto;
import com.task.entity.Employee;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface EmployeeReposiotry extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE "
			+ "(:search IS NULL OR e.name LIKE %:search% OR e.department LIKE %:search%)")
	Page<IListEmployeeDto> getAllEmployee(@Param("search") String search, Pageable pageable);

}
