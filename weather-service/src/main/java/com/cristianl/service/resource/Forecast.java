package com.cristianl.service.resource;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "forecast details such as celsius degrees, cloud, rain and snow probabilities")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Forecast implements java.io.Serializable {

	/* ___LOCATION___ */

	@JsonProperty("id")
	private Integer cityId;

	@JsonProperty("name")
	private String cityName;

	/**
	 * Country code (GB, JP etc.)
	 */
	@JsonProperty("sys.country")
	private String country;

	/**
	 * City geo location, latitude
	 */
	@JsonProperty("coord.lat")
	private Double latitude;

	/**
	 * City geo location, longitude
	 */
	@JsonProperty("coord.lon")
	private Double longitude;

	/* ___TIME__ */

	/**
	 * Shift in seconds from UTC
	 */
	@JsonProperty("timezone")
	private Long timezone;

	/**
	 * Time of data calculation, unix, UTC
	 */
	@JsonProperty("dt")
	private Long timestamp;

	/**
	 * Sunrise time, unix, UTC
	 */
	@JsonProperty("sys.sunrise")
	private Integer sunrise;

	/**
	 * Sunset time, unix, UTC
	 */
	@JsonProperty("sys.sunset")
	private Integer sunset;

	/* ___WEATHER___ */

	@JsonProperty("weather.id")
	private Integer weatherId;

	/**
	 * Group of weather parameters (Rain, Snow, Extreme etc.)
	 */
	@JsonProperty("weather.main")
	private String weatherValue;

	@JsonProperty("weather.description")
	private String weatherDesc;

	@JsonProperty("weather.icon")
	private String weatherIcon;

	/**
	 * Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
	 */
	@JsonProperty("main.temp")
	private Double temperature;

	/**
	 * Atmospheric pressure at sea level or ground level in hPa
	 */
	@JsonProperty("main.pressure")
	private Double pressure;

	/**
	 * percentage
	 */
	@JsonProperty("main.humidity")
	private Double humidity;

	/**
	 * Maximum temperature at the moment in large cities and megalopolises
	 */
	@JsonProperty("main.temp_max")
	private Double tempMax;

	/**
	 * Maximum temperature at the moment in large cities and megalopolises
	 */
	@JsonProperty("main.temp_min")
	private Double tempMin;

	/**
	 * Atmospheric pressure on the sea level in hPa
	 */
	@JsonProperty("main.sea_level")
	private Double seaLevelPressure;

	/**
	 * Atmospheric pressure on the ground level in hPa
	 */
	@JsonProperty("main.grnd_level")
	private Double groundLevelPressure;

	@JsonProperty("visibility")
	private Integer visibility;

	/* ___WIND___ */

	/**
	 * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
	 */
	@JsonProperty("wind.speed")
	private Double windSpeed;

	/**
	 * Wind direction, degrees (meteorological)
	 */
	@JsonProperty("wind.deg")
	private Double windDegrees;

	/* ___CLOUDS___ */

	/**
	 * Percentage
	 */
	@JsonProperty("clouds.all")
	private Integer cloudiness;

	/* ___RAIN___ */

	/**
	 * Rain volume for the last 1 hour in mm
	 */
	@JsonProperty("rain.1h")
	private Double rainVolume1;

	/**
	 * Rain volume for the last 3 hours in mm
	 */
	@JsonProperty("rain.3h")
	private Double rainVolume3;

	/* ___SNOW___ */

	/**
	 * Snow volume for the last 1 hour in mm
	 */
	@JsonProperty("snow.1h")
	private Double snowVolume1;

	/**
	 * Snow volume for the last 3 hours in mm
	 */
	@JsonProperty("snow.3h")
	private Double snowVolume3;

	/* ___OTHER___ */

	@JsonIgnore
	final java.util.Map<String, Object> properties = new java.util.HashMap<String, Object>(10);

	public Forecast() {
	}

	@JsonSetter("coord")
	public void setCoord(final java.util.Map<String, Object> values) {
		this.latitude = toDouble(values.get("lat"));
		this.longitude = toDouble(values.get("lon"));
	}

	@JsonGetter("coord")
	public java.util.Map<String, Object> getCoord() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("lat", this.latitude);
		result.put("lon", this.longitude);
		return result;
	}

	@JsonSetter("weather")
	public void setWeather(final java.util.List<java.util.Map<String, Object>> array) {
		final java.util.Map<String, Object> values = array.get(0);
		this.weatherId = (Integer) values.get("id");
		this.weatherValue = (String) values.get("main");
		this.weatherDesc = (String) values.get("description");
		this.weatherIcon = (String) values.get("icon");
	}

	@JsonGetter("weather")
	public java.util.Map<String, Object> getWeather() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("id", this.weatherId);
		result.put("main", this.weatherValue);
		result.put("description", this.weatherDesc);
		result.put("icon", this.weatherIcon);

		final java.util.List<java.util.Map<String, Object>> retValue = new java.util.ArrayList<java.util.Map<String, Object>>(
				1);
		retValue.add(result);
		return result;
	}

	@JsonSetter("main")
	public void setMain(final java.util.Map<String, Object> values) {
		this.temperature = toDouble(values.get("temp"));
		this.pressure = toDouble(values.get("pressure"));
		this.humidity = toDouble(values.get("humidity"));
		this.tempMin = toDouble(values.get("temp_min"));
		this.tempMax = toDouble(values.get("temp_max"));
		this.seaLevelPressure = toDouble(values.get("sea_level"));
		this.groundLevelPressure = toDouble(values.get("brnd_level"));
	}

	@JsonGetter("main")
	public java.util.Map<String, Object> getMain() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("temp", this.temperature);
		result.put("pressure", this.pressure);
		result.put("humidity", this.humidity);
		result.put("temp_min", this.tempMin);
		result.put("temp_max", this.tempMax);
		result.put("sea_level", this.seaLevelPressure);
		result.put("grnd_level", this.groundLevelPressure);
		return result;
	}

	@JsonSetter("clouds")
	public void setClouds(final java.util.Map<String, Object> values) {
		this.cloudiness = (Integer) values.get("all");
	}

	@JsonGetter("clouds")
	public java.util.Map<String, Object> getClouds() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("all", this.cloudiness);
		return result;
	}

	@JsonSetter("wind")
	public void setWind(final java.util.Map<String, Object> values) {
		this.windSpeed = toDouble(values.get("speed"));
		this.windDegrees = toDouble(values.get("deg"));
	}

	@JsonGetter("wind")
	public java.util.Map<String, Object> getWind() {
		java.util.Map<String, Object> result = null;
		if (this.windSpeed != null || this.windDegrees != null) {
			result = new java.util.HashMap<String, Object>(2);
			result.put("speed", this.windSpeed);
			result.put("deg", this.windDegrees);
		}
		return result;
	}

	@JsonSetter("rain")
	public void setRain(final java.util.Map<String, Object> values) {
		this.rainVolume1 = toDouble(values.get("1h"));
		this.rainVolume3 = toDouble(values.get("3h"));
	}

	@JsonGetter("rain")
	public java.util.Map<String, Object> getRain() {
		java.util.Map<String, Object> result = null;
		if (this.rainVolume1 != null || this.rainVolume3 != null) {
			result = new java.util.HashMap<String, Object>(2);
			result.put("1h", this.rainVolume1);
			result.put("3h", this.rainVolume3);
		}
		return result;
	}

	@JsonSetter("snow")
	public void setSnow(final java.util.Map<String, Object> values) {
		this.snowVolume1 = toDouble(values.get("1h"));
		this.snowVolume3 = toDouble(values.get("3h"));
	}

	@JsonGetter("snow")
	public java.util.Map<String, Object> getSnow() {
		java.util.Map<String, Object> result = null;
		if (this.snowVolume1 != null || this.snowVolume3 != null) {
			result = new java.util.HashMap<String, Object>(2);
			result.put("1h", this.snowVolume1);
			result.put("3h", this.snowVolume3);
		}
		return result;
	}

	@JsonSetter("sys")
	public void setSystem(final java.util.Map<String, Object> values) {
		this.properties.put("sys.id", values.get("id"));
		this.properties.put("sys.type", values.get("type"));
		this.properties.put("sys.message", values.get("message"));
		this.country = (String) values.get("country");
		this.sunrise = (Integer) values.get("sunrise");
		this.sunset = (Integer) values.get("sunset");
	}

	@JsonGetter("sys")
	public java.util.Map<String, Object> getSystem() {
		java.util.Map<String, Object> result = new java.util.HashMap<String, Object>(2);
		result.put("id", this.properties.get("sys.id"));
		result.put("type", this.properties.get("sys.type"));
		result.put("message", this.properties.get("sys.message"));
		result.put("country", this.country);
		result.put("sunrise", this.sunrise);
		result.put("sunset", this.sunset);
		return result;
	}

	@JsonAnySetter
	public void setProperties(final String name, final Object value) {
		this.properties.put(name, value);
	}

	@JsonAnyGetter
	public Object getProperties(final String name) {
		return this.properties.get(name);
	}

	@JsonIgnore
	public Integer getCityId() {
		return cityId;
	}

	@JsonIgnore
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@JsonIgnore
	public String getCityName() {
		return cityName;
	}

	@JsonIgnore
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonIgnore
	public String getCountry() {
		return country;
	}

	@JsonIgnore
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonIgnore
	public Double getLatitude() {
		return latitude;
	}

	@JsonIgnore
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@JsonIgnore
	public Double getLongitude() {
		return longitude;
	}

	@JsonIgnore
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@JsonIgnore
	public Long getTimezone() {
		return timezone;
	}

	@JsonIgnore
	public void setTimezone(Long timezone) {
		this.timezone = timezone;
	}

	@JsonIgnore
	public Long getTimestamp() {
		return timestamp;
	}

	@JsonIgnore
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@JsonIgnore
	public Integer getSunrise() {
		return sunrise;
	}

	@JsonIgnore
	public void setSunrise(Integer sunrise) {
		this.sunrise = sunrise;
	}

	@JsonIgnore
	public Integer getSunset() {
		return sunset;
	}

	@JsonIgnore
	public void setSunset(Integer sunset) {
		this.sunset = sunset;
	}

	@JsonIgnore
	public Integer getWeatherId() {
		return weatherId;
	}

	@JsonIgnore
	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	@JsonIgnore
	public String getWeatherValue() {
		return weatherValue;
	}

	@JsonIgnore
	public void setWeatherValue(String weatherValue) {
		this.weatherValue = weatherValue;
	}

	@JsonIgnore
	public String getWeatherDesc() {
		return weatherDesc;
	}

	@JsonIgnore
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}

	@JsonIgnore
	public String getWeatherIcon() {
		return weatherIcon;
	}

	@JsonIgnore
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@JsonIgnore
	public Double getTemperature() {
		return temperature;
	}

	@JsonIgnore
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	@JsonIgnore
	public Double getPressure() {
		return pressure;
	}

	@JsonIgnore
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	@JsonIgnore
	public Double getHumidity() {
		return humidity;
	}

	@JsonIgnore
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	@JsonIgnore
	public Double getTempMax() {
		return tempMax;
	}

	@JsonIgnore
	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	@JsonIgnore
	public Double getTempMin() {
		return tempMin;
	}

	@JsonIgnore
	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	@JsonIgnore
	public Double getSeaLevelPressure() {
		return seaLevelPressure;
	}

	@JsonIgnore
	public void setSeaLevelPressure(Double seaLevelPressure) {
		this.seaLevelPressure = seaLevelPressure;
	}

	@JsonIgnore
	public Double getGroundLevelPressure() {
		return groundLevelPressure;
	}

	@JsonIgnore
	public void setGroundLevelPressure(Double groundLevelPressure) {
		this.groundLevelPressure = groundLevelPressure;
	}

	@JsonIgnore
	public Integer getVisibility() {
		return visibility;
	}

	@JsonIgnore
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	@JsonIgnore
	public Double getWindSpeed() {
		return windSpeed;
	}

	@JsonIgnore
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	@JsonIgnore
	public Double getWindDegrees() {
		return windDegrees;
	}

	@JsonIgnore
	public void setWindDegrees(Double windDegrees) {
		this.windDegrees = windDegrees;
	}

	@JsonIgnore
	public Integer getCloudiness() {
		return cloudiness;
	}

	@JsonIgnore
	public void setCloudiness(Integer cloudiness) {
		this.cloudiness = cloudiness;
	}

	@JsonIgnore
	public Double getRainVolume1() {
		return rainVolume1;
	}

	@JsonIgnore
	public void setRainVolume1(Double rainVolume1) {
		this.rainVolume1 = rainVolume1;
	}

	@JsonIgnore
	public Double getRainVolume3() {
		return rainVolume3;
	}

	@JsonIgnore
	public void setRainVolume3(Double rainVolume3) {
		this.rainVolume3 = rainVolume3;
	}

	@JsonIgnore
	public Double getSnowVolume1() {
		return snowVolume1;
	}

	@JsonIgnore
	public void setSnowVolume1(Double snowVolume1) {
		this.snowVolume1 = snowVolume1;
	}

	@JsonIgnore
	public Double getSnowVolume3() {
		return snowVolume3;
	}

	@JsonIgnore
	public void setSnowVolume3(Double snowVolume3) {
		this.snowVolume3 = snowVolume3;
	}

	private static Double toDouble(final Object value) {
		if (value instanceof Double) {
			return (Double) value;
		} else if (value instanceof Number) {
			return ((Number) value).doubleValue();
		} else if (value != null) {
			return Double.valueOf(value.toString());
		} else {
			return null;
		}
	}

}
