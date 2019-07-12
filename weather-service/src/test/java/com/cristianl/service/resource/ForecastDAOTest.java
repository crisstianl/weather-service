package com.cristianl.service.resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.cristianl.service.api.OpenWeatherResource;

public class ForecastDAOTest {

	@Test
	public void getWeatherByCityIdTest() throws Exception {
		final ForecastDAO dao = new ForecastDAO(getResource());
		final Forecast result = dao.get(2172797, null, null);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getCityName());
		Assert.assertEquals("London", result.getCityName());
	}

	@Test
	public void getWeatherByCityNameTest() throws Exception {
		final ForecastDAO dao = new ForecastDAO(getResource());
		final Forecast result = dao.get(null, "London", null);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getCityName());
		Assert.assertEquals("London", result.getCityName());
	}

	@Test
	public void getWeatherByCityZipcodeTest() throws Exception {
		final ForecastDAO dao = new ForecastDAO(getResource());
		final Forecast result = dao.get(null, null, "W1A0AX");
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getCityName());
		Assert.assertEquals("London", result.getCityName());
	}

	@Test
	public void getWeatherByPositionTest() throws Exception {
		final ForecastDAO dao = new ForecastDAO(getResource());
		final Forecast result = dao.get("45", "45");
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getCityName());
		Assert.assertEquals("London", result.getCityName());
	}

	private static OpenWeatherResource getResource() {
		OpenWeatherResource instance = Mockito.mock(OpenWeatherResource.class);
		Mockito.when(instance.getWeather(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(buildResponse());
		Mockito.when(instance.getWeather(Mockito.anyString(), Mockito.anyString())).thenReturn(buildResponse());
		return instance;
	}

	private static String buildResponse() {
		return "{\"id\" : 2172797, \"name\" : \"London\"}";
	}

}
