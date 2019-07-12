package com.cristianl.service.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class OpenWeatherResourceTest {

	private WireMockServer wiremock = null;

	@Before
	public void init() throws Exception {
		wiremock = new WireMockServer(WireMockConfiguration.options().port(56789));
		wiremock.start();

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
	public void getWeatherByCityIdTest() {
		OpenWeatherResource instance = new OpenWeatherResource();
		String response = instance.getWeather(2172797, null, null);
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void getWeatherByCityNameTest() {
		OpenWeatherResource instance = new OpenWeatherResource();
		String response = instance.getWeather(null, "London", null);
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void getWeatherByZipcodeTest() {
		OpenWeatherResource instance = new OpenWeatherResource();
		String response = instance.getWeather(null, null, "W1A0AX");
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void getWeatherByPositionTest() {
		OpenWeatherResource instance = new OpenWeatherResource();
		String response = instance.getWeather("45", "45");
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

}
