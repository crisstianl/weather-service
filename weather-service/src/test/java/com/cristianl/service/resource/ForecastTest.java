package com.cristianl.service.resource;

public class ForecastTest extends PojoTest<Forecast> {

	@Override
	protected Forecast getInstance() {
		return new Forecast();
	}

	@Override
	protected String[] ignoreFields() {
		return new String[] { "properties" };
	}

}
