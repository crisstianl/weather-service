package com.cristianl.service.resource;

import org.junit.Assert;
import org.junit.Test;

public class LocationDAOTest {

	@Test
	public void getAllTest() throws Exception {
		LocationDAO instance = new LocationDAO();
		Location[] results = instance.getAll("europe");
		Assert.assertNotNull(results);
		Assert.assertTrue(results.length > 0);
	}

}
