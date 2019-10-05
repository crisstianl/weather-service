package com.cristianl.service.exception;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

public class WeatherServiceExceptionHandlerTest {

	@Test
	public void ctorTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		Assert.assertNotNull(instance);
	}

	@Test
	public void handleExceptionsTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleExceptions(getException(), getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceException);
	}

	@Test
	public void handleStatusExceptionsTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleStatusExceptions(getResponseStatusException(), getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceException);
	}

	@Test
	public void handleWeatherExceptionTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleWeatherException(getException(), getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceException);
	}

	@Test
	public void handleWeatherNotFoundExceptionTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleWeatherNotFoundException(getException(), getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceNotFoundException);
	}

	@Test
	public void handleWeatherBadRequestExceptionTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleWeatherBadRequestException(getException(), getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceBadRequestException);
	}

	@Test
	public void handleMethodArgumentNotValidTest() {
		WeatherServiceExceptionHandler instance = new WeatherServiceExceptionHandler();
		ResponseEntity<Object> result = instance.handleMethodArgumentNotValid(getArgumentNotValidException(), null,
				HttpStatus.BAD_REQUEST, getRequest());
		Assert.assertNotNull(result);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody() instanceof WeatherServiceException);
	}

	private static Exception getException() {
		return new Exception("error");
	}

	private static ResponseStatusException getResponseStatusException() {
		return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error");
	}

	private static MethodArgumentNotValidException getArgumentNotValidException() {
		MethodParameter param = Mockito.mock(MethodParameter.class);
		BindingResult result = Mockito.mock(BindingResult.class);
		return new MethodArgumentNotValidException(param, result);
	}

	private static WebRequest getRequest() {
		WebRequest instance = Mockito.mock(WebRequest.class);
		Mockito.when(instance.getDescription(Mockito.anyBoolean())).thenReturn("error");
		return instance;
	}
}
