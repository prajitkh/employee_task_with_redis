package com.task.weather;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService service;

	@GetMapping("/getWeatherInfo")
	public ResponseEntity<?> getWeatherInformation(@RequestParam String city) {

		try {
			Map<String, Object> weather = service.getWeather(city);

			return new ResponseEntity<>(new Response("Get weather inforamtion Successfully", weather), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
