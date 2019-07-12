package com.cristianl.service.resource;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "the geographical name and coordinates of the location")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Location implements java.io.Serializable {

	@JsonProperty
	private Integer id;

	@ApiModelProperty(notes = "name should be atleast 2 chars long")
	@Size(min = 2, message = "name should be atleast 2 chars long")
	@JsonProperty
	private String name;

	@Size(max = 2)
	@JsonProperty
	private String country;

	@JsonProperty
	private String region;

	@JsonProperty("lat")
	private Double latitude;

	@JsonProperty("lon")
	private Double longitude;

	public Location() {
	}

	public Location(Integer id, String name, String country, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@JsonSetter("coord")
	public void setCoord(final java.util.Map<String, Object> values) {
		if (values != null && !values.isEmpty()) {
			this.latitude = ((Number) values.get("lat")).doubleValue();
			this.longitude = ((Number) values.get("lon")).doubleValue();
		}
	}

	@JsonGetter("coord")
	public java.util.Map<String, Object> getCoord() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("lat", this.latitude);
		result.put("lon", this.longitude);
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
