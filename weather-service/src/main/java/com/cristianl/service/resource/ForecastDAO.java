package com.cristianl.service.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.cristianl.service.api.OpenWeatherResource;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ForecastDAO {

	@Autowired
	private OpenWeatherResource resource;

	/**
	 * Visible for testing
	 */
	public ForecastDAO(final OpenWeatherResource resource) {
		this.resource = resource;
	}

	public Forecast get(final Integer cityId, final String cityName, final String zipcode)
			throws ResponseStatusException {
		try {
			final String jsonResp = this.resource.getWeather(cityId, cityName, zipcode);
			final ObjectMapper mapper = new ObjectMapper();
			return (Forecast) mapper.readValue(jsonResp, Forecast.class);
			// return get();
		} catch (ResponseStatusException e) {
			throw e;
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private static Forecast get() throws IOException {
		final Resource jsonRes = new ClassPathResource("static/London.json");
		final ObjectMapper mapper = new ObjectMapper();
		return (Forecast) mapper.readValue(jsonRes.getInputStream(), Forecast.class);
	}

	public Forecast get(final String lat, final String lon) {
		try {
			final String jsonResp = this.resource.getWeather(lat, lon);
			final ObjectMapper mapper = new ObjectMapper();
			return (Forecast) mapper.readValue(jsonResp, Forecast.class);
		} catch (ResponseStatusException e) {
			throw e;
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	public void upsert(final Forecast newForecast) {
	}

	public Forecast remove(final String city) {
		Forecast result = new Forecast();
		result.setCityName(city);
		return result;
	}

}
