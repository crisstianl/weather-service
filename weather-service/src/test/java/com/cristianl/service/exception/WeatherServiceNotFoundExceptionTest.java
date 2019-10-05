package com.cristianl.service.exception;

import org.junit.Assert;
import org.junit.Test;

public class WeatherServiceNotFoundExceptionTest {

	@Test
	public void ctorTest() {
		final WeatherServiceNotFoundException instance = getInstance();
		Assert.assertNotNull(instance);

	}

	@Test
	public void gettersTest() {
		final WeatherServiceNotFoundException instance = getInstance();
		Assert.assertNull(instance.getStackTrace());
		Assert.assertNull(instance.getCause());
		Assert.assertNotNull(instance.getDetails());
		Assert.assertNotNull(instance.getTimestamp());
	}

	private static WeatherServiceNotFoundException getInstance() {
		return new WeatherServiceNotFoundException("error", "big error");
	}
}
