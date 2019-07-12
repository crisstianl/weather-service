package com.cristianl.service.api;

import org.junit.Assert;
import org.junit.Test;

public class HelloResourceTest {

	@Test
	public void uri_version1Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.uri_version1());
	}

	@Test
	public void uri_version2Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.uri_version2());
	}

	@Test
	public void param_version1Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.param_version1());
	}

	@Test
	public void param_version2Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.param_version2());
	}

	@Test
	public void header_version1Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.header_version1());
	}

	@Test
	public void header_version2Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.header_version2());
	}

	@Test
	public void mediaType_version1Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.mediaType_version1());
	}

	@Test
	public void mediaType_version2Test() {
		HelloResource instance = new HelloResource();
		Assert.assertNotNull(instance.mediaType_version2());
	}
}
