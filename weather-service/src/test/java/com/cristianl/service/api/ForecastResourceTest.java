package com.cristianl.service.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cristianl.service.resource.Forecast;
import com.cristianl.service.resource.ForecastDAO;
import com.cristianl.service.resource.Location;
import com.cristianl.service.resource.LocationDAO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class ForecastResourceTest {

	private WireMockServer wiremock = null;

	@Before
	public void init() throws Exception {
		wiremock = new WireMockServer(WireMockConfiguration.options().port(56789));
		wiremock.start();

		/*
		 * wiremock.stubFor(get(urlPathMatching("/data/2.5/weather?q=London")).
		 * willReturn(aResponse().withStatus(200) .withHeader("Content-Type",
		 * "application/json").withBodyFile("static/London.json")));
		 */

		wiremock.stubFor(get(urlMatching("/weather\\?id=2172797.*")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("London.json")));

		wiremock.stubFor(get(urlMatching("/weather\\?q=London.*")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("London.json")));

		wiremock.stubFor(get(urlMatching("/weather\\?zip=W1A0AX.*")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("London.json")));

		wiremock.stubFor(get(urlMatching("/weather\\?lat=45&lon=45.*")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("London.json")));
	}

	@After
	public void destroy() throws Exception {
		wiremock.stop();
		wiremock = null;
	}

	@Test
	public void pingTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<String> response = instance.ping(Locale.UK);

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void doGetAllCitiesTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<Location[]> response = instance.doGetAllCities(null);

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertTrue(response.getBody().length > 0);
	}

	@Test
	public void doGetByCityIdTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<Forecast> response = instance.doGetByCity("2172797");

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("London", response.getBody().getCityName());
	}

	@Test
	public void doGetByCityNameTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<Forecast> response = instance.doGetByCity("London");

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("London", response.getBody().getCityName());
	}

	@Test
	public void doGetByCityZipcodeTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<Forecast> response = instance.doGetByCity("W1A0AX");

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("London", response.getBody().getCityName());
	}

	@Test
	public void doGetByPositionTest() throws Exception {
		ForecastResource instance = getInstance();
		ResponseEntity<Forecast> response = instance.doGetByPosition("45", "45");

		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("London", response.getBody().getCityName());
	}

	private static ForecastResource getInstance() {
		ForecastResource instance = new ForecastResource(getLocationDAO(), getForecastDAO(), getMessages());
		return instance;
	}

	private static MessageSource getMessages() {
		MessageSource retValue = Mockito.mock(MessageSource.class);
		Mockito.when(retValue.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn("test");
		return retValue;
	}

	private static LocationDAO getLocationDAO() {
		return new LocationDAO();
	}

	private static ForecastDAO getForecastDAO() {
		return new ForecastDAO(new OpenWeatherResource());
	}
}
