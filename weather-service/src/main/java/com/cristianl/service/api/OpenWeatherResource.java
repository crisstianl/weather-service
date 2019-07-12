package com.cristianl.service.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OpenWeatherResource {

	private static final String API_KEY = "b6446fead4c9dbccd049cd8acc07f2db";
	private static final String API_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather";
	// private static final String API_ENDPOINT = "http://localhost:56789/weather";

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
		return get(urlParams);
	}

	public String getWeather(final String lat, final String lon) throws ResponseStatusException {
		final String urlParams = "?lat=" + lat + "&lon=" + lon;
		return get(urlParams);
	}

	private static String get(final String urlParams) throws ResponseStatusException {
		HttpURLConnection conn = null;
		try {
			// open connection
			final URL endpoint = new URL(API_ENDPOINT + urlParams + "&units=metric&APPID=" + API_KEY);
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

	private static String post(final String formParams) throws MalformedURLException, IOException {
		HttpURLConnection conn = null;
		try {
			// open connection
			final URL endpoint = new URL(API_ENDPOINT + "?units=metric&APPID=" + API_KEY);
			conn = (HttpURLConnection) endpoint.openConnection();

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
