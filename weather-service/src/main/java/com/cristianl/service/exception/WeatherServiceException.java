package com.cristianl.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WeatherServiceException extends Exception {

	private String details;

	private Date timestamp;

	public WeatherServiceException(final String message, final String details) {
		super(message);
		this.details = details;
		this.timestamp = new Date();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return null;
	}

	@Override
	public synchronized Throwable getCause() {
		return null;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}