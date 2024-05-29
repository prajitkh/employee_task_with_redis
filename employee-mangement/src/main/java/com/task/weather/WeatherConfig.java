package com.task.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfig {

	@Value("${api.key}")
	public String apiKey;

	public String getApiKey() {

		return this.apiKey;
	}

}
