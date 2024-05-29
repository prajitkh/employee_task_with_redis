package com.task.weather;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
	WeatherConfig config;

	@Autowired
	private WeatherService(WeatherConfig weatherConfig) {
		this.config = weatherConfig;
	}

	public Map<String, Object> getWeather(String city) {
		String apiKey = config.getApiKey();
		// String url =
		// String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
		// city, apiKey);

		String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city,
				apiKey);

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(url, Map.class);

	}

}
