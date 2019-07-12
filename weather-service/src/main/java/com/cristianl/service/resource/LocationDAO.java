package com.cristianl.service.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LocationDAO {

	/**
	 * Visible for testing
	 */
	public LocationDAO() {
		super();
	}

	public Location[] getAll(final String region) throws Exception {
		final Resource jsonRes = new ClassPathResource("static/cities.json");
		final ObjectMapper mapper = new ObjectMapper();
		return (Location[]) mapper.readValue(jsonRes.getInputStream(), Location[].class);
	}

}
