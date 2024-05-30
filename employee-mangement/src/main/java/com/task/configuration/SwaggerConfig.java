package com.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
//	@Bean
//	public GroupedOpenApi publicApi() {
//		return GroupedOpenApi.builder().group("public-api").pathsToMatch("/**").build();
//	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API Documentation").version("1.0")
				.description("API documentation with JWT authentication"));
		// .components(new Components().addSecuritySchemes("bearerAuth",
		// new
		// SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
		// .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
}