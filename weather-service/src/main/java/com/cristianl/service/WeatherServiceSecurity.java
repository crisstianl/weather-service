package com.cristianl.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WeatherServiceSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		// define security credentials
		auth.inMemoryAuthentication().withUser("admin").password(encode("perfectweather")).roles("USER");
		auth.inMemoryAuthentication().withUser("user").password(encode("perfectweather")).roles("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// restrict all unauthorized requests
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private String encode(final String str) {
		return passwordEncoder().encode(str);
	}

}
