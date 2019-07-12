package com.cristianl.service.resource;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest extends PojoTest<Location> {

	@Override
	protected Location getInstance() {
		return new Location(new Integer(1), "Paris", "FR", new Double(4.0D), new Double(6.0D));
	}

	@Override
	protected String[] ignoreFields() {
		return null;
	}

	@Test
	public void CtorTest2() {
		Location instance = new Location();
		Assert.assertNotNull(instance);
	}

	@Test
	public void setCoordTest() {
		final Double lat = new Double(3.0D);
		final Double lng = new Double(4.0D);
		java.util.HashMap<String, Object> coords = new java.util.HashMap<String, Object>(2);
		coords.put("lat", lat);
		coords.put("lon", lng);

		Location instance = new Location();
		instance.setCoord(coords);

		Assert.assertEquals(lat, instance.getLatitude());
		Assert.assertEquals(lng, instance.getLongitude());
	}

	@Test
	public void getCoordTest() {
		final Double lat = new Double(3.0D);
		final Double lng = new Double(4.0D);
		Location instance = new Location();
		instance.setLatitude(lat);
		instance.setLongitude(lng);

		java.util.Map<String, Object> coords = instance.getCoord();
		Assert.assertEquals(lat, coords.get("lat"));
		Assert.assertEquals(lng, coords.get("lon"));
	}
}
