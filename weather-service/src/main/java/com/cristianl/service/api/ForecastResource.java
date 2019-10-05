package com.cristianl.service.api;

import java.net.URI;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cristianl.service.exception.WeatherServiceNotFoundException;
import com.cristianl.service.resource.Forecast;
import com.cristianl.service.resource.ForecastDAO;
import com.cristianl.service.resource.Location;
import com.cristianl.service.resource.LocationDAO;
import com.cristianl.service.util.ServiceUtils;

// Controller
@RestController
@RequestMapping("/v1")
public class ForecastResource {

	@Autowired
	private LocationDAO locationDAO;

	@Autowired
	private ForecastDAO forecastDAO;

	@Autowired
	private MessageSource messages;

	public ForecastResource() {
		super();
	}

	/**
	 * Visible for testing
	 */
	ForecastResource(final LocationDAO locationDAO, final ForecastDAO forecastDAO, final MessageSource messages) {
		super();
		this.locationDAO = locationDAO;
		this.forecastDAO = forecastDAO;
		this.messages = messages;
	}

	/* ***GET*** */

	@RequestMapping(method = RequestMethod.GET, path = "/ping", produces = "application/text")
	public ResponseEntity<String> ping(@RequestHeader(name = "Accept-Language", required = false) Locale locale)
			throws Exception {
		final String result = this.messages.getMessage("hello.message", null, locale);
		return ResponseEntity.ok(result);
	}

	@GetMapping(path = "/cities/{region}", produces = "application/json")
	public ResponseEntity<Location[]> doGetAllCities(@PathVariable(required = false) String region) throws Exception {
		return ResponseEntity.ok(this.locationDAO.getAll(region));
	}

	@GetMapping(path = "/forecast/{city}", produces = "application/json")
	public ResponseEntity<Forecast> doGetByCity(@PathVariable(required = true) String city) throws Exception {
		Forecast result = null;
		if (ServiceUtils.isNumeric(city)) {
			result = forecastDAO.get(Integer.valueOf(city), null, null);
		} else if (ServiceUtils.isText(city)) {
			result = forecastDAO.get(null, city, null);
		} else if (ServiceUtils.isZipcode(city)) {
			result = forecastDAO.get(null, null, city);
		}

		if (result != null) {
			return ResponseEntity.ok(result);
		}
		throw new WeatherServiceNotFoundException("City not found " + city, null);
	}

	@GetMapping(path = "/forecast", produces = "application/json")
	public ResponseEntity<Forecast> doGetByPosition(@RequestParam(required = true) String lat,
			@RequestParam(required = true) String lon) throws Exception {
		Forecast result = forecastDAO.get(lat, lon);
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		throw new WeatherServiceNotFoundException("Location not found ", null);
	}

	/* ***POST*** */

	@PostMapping(path = "/forecast", consumes = "application/json", produces = "application/text")
	public ResponseEntity<String> insert(@Valid @RequestBody Forecast newForecast) throws Exception {
		forecastDAO.upsert(newForecast);
		final URI newAddress = ServletUriComponentsBuilder.fromCurrentRequest().path("/{city}")
				.buildAndExpand(newForecast.getCityName()).toUri();
		return ResponseEntity.created(newAddress).build();
	}

	/* ***PUT*** */

	@PutMapping(path = "/forecast", consumes = "application/json", produces = "application/text")
	public ResponseEntity<String> update(@Valid @RequestBody Forecast newForecast) throws Exception {
		forecastDAO.upsert(newForecast);
		final URI newAddress = ServletUriComponentsBuilder.fromCurrentRequest().path("/{city}")
				.buildAndExpand(newForecast.getCityName()).toUri();
		return ResponseEntity.created(newAddress).build();
	}

	/* ***DELETE*** */

	@DeleteMapping(path = "/forecast/{city}", produces = "application/text")
	public ResponseEntity<String> remove(@PathVariable @NotNull String city) throws Exception {
		Forecast forecast = new Forecast();
		if (ServiceUtils.isNumeric(city)) {
			forecast.setCityId(Integer.valueOf(city));
		} else {
			forecast.setCityName(city);
		}

		if (!forecastDAO.remove(forecast)) {
			throw new WeatherServiceNotFoundException("City not found " + city, null);
		}
		return ResponseEntity.noContent().build();
	}

}
