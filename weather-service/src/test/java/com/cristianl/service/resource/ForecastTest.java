package com.cristianl.service.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ForecastTest extends PojoTest<Forecast> {

	@Override
	protected Forecast getInstance() {
		return new Forecast();
	}

	@Override
	protected String[] ignoreFields() {
		return new String[] { "properties" };
	}

	@Test
	public void getsetPropertiesTest() {
		final Forecast instance = new Forecast();
		instance.setProperties("sys.main", "x");
		Assert.assertNotNull(instance.getProperties("sys.main"));
	}

	@Test
	public void getsetCoordTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("lat", new Double(10), "lon", new Double(10));

		instance.setCoord(args);
		Assert.assertNotNull(instance.getCoord());
		Assert.assertEquals(args.size(), instance.getCoord().size());
	}

	@Test
	public void getsetWeatherTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("id", new Integer(123456), "main", "x", "description", "y",
				"icon", "z");

		instance.setWeather(toList(args));
		Assert.assertNotNull(instance.getWeather());
		Assert.assertEquals(args.size(), instance.getWeather().size());
	}

	@Test
	public void getsetMainTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("temp", new Double(10), "pressure", new Double(10), "humidity",
				new Double(10), "temp_min", new Double(10), "temp_max", new Double(10), "sea_level", new Double(10),
				"brnd_level", "20");

		instance.setMain(args);
		Assert.assertNotNull(instance.getMain());
		Assert.assertEquals(args.size(), instance.getMain().size());
	}

	@Test
	public void getsetCloudsTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("all", new Integer(10));

		instance.setClouds(args);
		Assert.assertNotNull(instance.getClouds());
		Assert.assertEquals(args.size(), instance.getClouds().size());
	}

	@Test
	public void getsetWindTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("speed", new Double(10), "deg", new Double(20));

		instance.setWind(args);
		Assert.assertNotNull(instance.getWind());
		Assert.assertEquals(args.size(), instance.getWind().size());
	}

	@Test
	public void getsetRainTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("1h", new Double(10), "3h", new Double(20));

		instance.setRain(args);
		Assert.assertNotNull(instance.getRain());
		Assert.assertEquals(args.size(), instance.getRain().size());
	}

	@Test
	public void getsetSnowTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("1h", new Double(10), "3h", new Double(20));

		instance.setSnow(args);
		Assert.assertNotNull(instance.getSnow());
		Assert.assertEquals(args.size(), instance.getSnow().size());
	}

	@Test
	public void getsetSystemTest() {
		final Forecast instance = new Forecast();
		final HashMap<String, Object> args = toHashMap("id", new Integer(10), "type", "x", "message", "y", "country",
				"z", "sunrise", new Integer(1), "sunset", new Integer(2));

		instance.setSystem(args);
		Assert.assertNotNull(instance.getSystem());
		Assert.assertEquals(args.size(), instance.getSystem().size());
	}

	private static HashMap<String, Object> toHashMap(final Object... args) {
		final HashMap<String, Object> retMap = new HashMap<String, Object>(args.length);
		for (int i = 0; i < args.length; i += 2) {
			retMap.put((String) args[i], args[i + 1]);
		}
		return retMap;
	}

	private static ArrayList<Map<String, Object>> toList(final HashMap<String, Object> arg) {
		ArrayList<Map<String, Object>> retList = new ArrayList<Map<String, Object>>(1);
		retList.add(arg);
		return retList;
	}

}
