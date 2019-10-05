package com.cristianl.service.exception;

import org.junit.Assert;
import org.junit.Test;

public class WeatherServiceExceptionTest {

	@Test
	public void ctorTest() {
		final WeatherServiceException instance = getInstance();
		Assert.assertNotNull(instance);

	}

	@Test
	public void gettersTest() {
		final WeatherServiceException instance = getInstance();
		Assert.assertNull(instance.getStackTrace());
		Assert.assertNull(instance.getCause());
		Assert.assertNotNull(instance.getDetails());
		Assert.assertNotNull(instance.getTimestamp());
	}

	private static WeatherServiceException getInstance() {
		return new WeatherServiceException("error", "big error");
	}

}
