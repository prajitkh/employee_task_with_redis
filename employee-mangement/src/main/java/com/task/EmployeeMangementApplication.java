package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmployeeMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMangementApplication.class, args);
	}

}
