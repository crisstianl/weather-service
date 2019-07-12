package com.cristianl.service;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Contact API_CONTACT = new Contact("Cristian", "http://localhost:8080", "cristian@gmail.com");

	private static final ApiInfo API_INFO = new ApiInfo("Weather service",
			"Weather service helps you pick your umbrella during rains and sunscream during scorching afternoons",
			"1.0", "urn:tos", API_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final HashSet<String> API_PRODUCES = new HashSet<String>(2);

	private static final HashSet<String> API_CONSUMES = new HashSet<String>(2);

	static {
		API_PRODUCES.add("application/json");
		API_PRODUCES.add("application/xml");
		API_CONSUMES.add("application/json");
		API_CONSUMES.add("application/xml");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO).produces(API_PRODUCES).consumes(API_CONSUMES);
	}
}
