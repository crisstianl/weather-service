package com.cristianl.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class WeatherServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(new WeatherServiceException(ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public final ResponseEntity<Object> handleStatusExceptions(Exception ex, WebRequest request) {
		final ResponseStatusException statusEx = (ResponseStatusException) ex;
		return new ResponseEntity<Object>(
				new WeatherServiceException(statusEx.getMessage(), request.getDescription(false)),
				statusEx.getStatus());
	}

	@ExceptionHandler(WeatherServiceException.class)
	public final ResponseEntity<Object> handleWeatherException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(new WeatherServiceException(ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(WeatherServiceNotFoundException.class)
	public final ResponseEntity<Object> handleWeatherNotFoundException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new WeatherServiceNotFoundException(ex.getMessage(), request.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(WeatherServiceBadRequestException.class)
	public final ResponseEntity<Object> handleWeatherBadRequestException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new WeatherServiceBadRequestException(ex.getMessage(), request.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(
				new WeatherServiceException("Validation failed", ex.getBindingResult().toString()), status);
	}
}
