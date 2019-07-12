package com.cristianl.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeatherServiceNotFoundException extends WeatherServiceException {

	public WeatherServiceNotFoundException(final String message, final String details) {
		super(message, details);
	}

}
