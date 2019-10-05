package com.cristianl.service.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OpenWeatherResource {

	@Value("${openweathermap.api-key}")
	private String apiKey;

	@Value("${openweathermap.api-url}")
	private String apiUrl;

	public OpenWeatherResource() {
		super();
	}

	/**
	 * Visible for testing
	 */
	OpenWeatherResource(final String apiKey, final String apiUrl) {
		this.apiKey = apiKey;
		this.apiUrl = apiUrl;
	}

	public String getWeather(final Integer cityId, final String cityName, final String zipcode)
			throws ResponseStatusException {
		final String urlParams;
		if (cityId != null) {
			urlParams = "?id=" + cityId;
		} else if (cityName != null) {
			urlParams = "?q=" + cityName;
		} else if (zipcode != null) {
			urlParams = "?zip=" + zipcode;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must specify either the city id, city name or zipcode");
		}
		return get(this.apiUrl, urlParams, this.apiKey);
	}

	public String getWeather(final String lat, final String lon) throws ResponseStatusException {
		final String urlParams = "?lat=" + lat + "&lon=" + lon;
		return get(this.apiUrl, urlParams, this.apiKey);
	}

	public String addWeather(final String cityId, final String weatherId) throws ResponseStatusException {
		final String formParams = String.format("id=%s&weather=%s", cityId, weatherId);
		return post(this.apiUrl, formParams, this.apiKey);
	}

	private static String get(final String apiUrl, final String urlParams, final String apiKey)
			throws ResponseStatusException {
		HttpURLConnection conn = null;
		try {
			// open connection
			final URL endpoint = new URL(apiUrl + urlParams + "&units=metric&APPID=" + apiKey);
			conn = (HttpURLConnection) endpoint.openConnection();

			// set headers
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			// send request
			final int statusCode = conn.getResponseCode();
			if (statusCode >= 300) { // error
				throw new ResponseStatusException(HttpStatus.valueOf(statusCode));
			}

			// process response
			return toString(conn.getInputStream());
		} catch (MalformedURLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private static String post(final String apiUrl, final String formParams, final String apiKey)
			throws ResponseStatusException {
		HttpURLConnection conn = null;
		try {
			// open connection
			final URL endpointURL = new URL(apiUrl + "?units=metric&APPID=" + apiKey);
			conn = (HttpURLConnection) endpointURL.openConnection();

			// set headers
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Language", "en-US");
			conn.setRequestProperty("Content-Length", Integer.toString(formParams.getBytes().length));
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			// set body
			final DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(formParams);
			wr.close();

			// send request
			final int statusCode = conn.getResponseCode();
			if (statusCode >= 300) { // error
				throw new ResponseStatusException(HttpStatus.valueOf(statusCode));
			}

			// process response
			return toString(conn.getInputStream());
		} catch (MalformedURLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private static String toString(final InputStream is) throws IOException {
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(is));

			final StringBuilder response = new StringBuilder();
			String line;
			while ((line = bfr.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			return response.toString();
		} finally {
			if (is != null) {
				is.close();
			}
			if (bfr != null) {
				bfr.close();
			}
		}
	}

}
