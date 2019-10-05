package com.cristianl.service.exception;

import org.junit.Assert;
import org.junit.Test;

public class WeatherServiceBadRequestExceptionTest {

	@Test
	public void ctorTest() {
		final WeatherServiceBadRequestException instance = getInstance();
		Assert.assertNotNull(instance);

	}

	@Test
	public void gettersTest() {
		final WeatherServiceBadRequestException instance = getInstance();
		Assert.assertNull(instance.getStackTrace());
		Assert.assertNull(instance.getCause());
		Assert.assertNotNull(instance.getDetails());
		Assert.assertNotNull(instance.getTimestamp());
	}

	private static WeatherServiceBadRequestException getInstance() {
		return new WeatherServiceBadRequestException("error", "big error");
	}

}
