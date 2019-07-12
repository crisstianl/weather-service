package com.cristianl.service.util;

import org.junit.Assert;
import org.junit.Test;

public class ServiceUtilsTest {

	@Test
	public void testIsNumber() {
		Assert.assertTrue(ServiceUtils.isNumeric("12345678"));
		Assert.assertTrue(ServiceUtils.isNumeric("1234.5678"));
		Assert.assertTrue(ServiceUtils.isNumeric("-1234.5678"));
		Assert.assertFalse(ServiceUtils.isNumeric("1234xyz"));
		Assert.assertFalse(ServiceUtils.isNumeric("1234x5678"));
		Assert.assertFalse(ServiceUtils.isNumeric("1234-5678"));
	}

	@Test
	public void testIsText() {
		Assert.assertTrue(ServiceUtils.isText("abcDEF"));
		Assert.assertFalse(ServiceUtils.isText("a1b2c3"));
		Assert.assertFalse(ServiceUtils.isText("abc.def"));
		Assert.assertFalse(ServiceUtils.isText("(abc def)"));
	}

	@Test
	public void testIsZipcode() {
		// US codes
		Assert.assertTrue(ServiceUtils.isZipcode("12345"));
		Assert.assertTrue(ServiceUtils.isZipcode("123456789"));
		Assert.assertTrue(ServiceUtils.isZipcode("12345 6789"));

		// UK codes
		Assert.assertTrue(ServiceUtils.isZipcode("CW3 9SS"));
		Assert.assertTrue(ServiceUtils.isZipcode("SE50EG"));
		Assert.assertTrue(ServiceUtils.isZipcode("se5 0eg"));
		Assert.assertTrue(ServiceUtils.isZipcode("WC2H 7LT"));
	}

}
