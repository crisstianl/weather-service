package com.cristianl.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void configureTest() {
		WeatherServiceApplication instance = getInstance();
		instance.configure(getBuilder());
		Assert.assertNotNull(instance);
	}

	@Test
	public void localeResolverTest() {
		WeatherServiceApplication instance = getInstance();
		LocaleResolver result = instance.localeResolver();
		Assert.assertNotNull(result);
	}

	@Test
	public void buildMessageSourceTest() {
		WeatherServiceApplication instance = getInstance();
		ResourceBundleMessageSource result = instance.buildMessageSource();
		Assert.assertNotNull(result);
	}

	private static WeatherServiceApplication getInstance() {
		return new WeatherServiceApplication();
	}

	private static SpringApplicationBuilder getBuilder() {
		return Mockito.mock(SpringApplicationBuilder.class);
	}

}
