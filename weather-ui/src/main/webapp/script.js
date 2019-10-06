// global variables
var gmap;
var gmarker;

window.onload = function() {
	console.log("page load...");
	getCities(showCityList, showError);
}

function getPosition() {	
	if (navigator.geolocation) { // html 5
		navigator.geolocation.getCurrentPosition(function(position) { // success
			console.log("Current geolocation latitude=" + position.coords.latitude + " and longitude=" + position.coords.longitude);
			showPosition(position.coords.latitude, position.coords.longitude);
			
		}, function() { // error
			// Germany, downtown Munich
			console.log("Default geolocation downtown Munich DE, latitude=48.1365, and longitude=11.5760");
			showPosition(48.1365, 11.5760);
			
		}, {timeout: 5 * 1000});
	} else {
		console.log("Geolocation is not supported or turned off by this browser.");
		console.log("Default geolocation downtown Munich DE, latitude=48.1365, and longitude=11.5760");
		showPosition(48.1365, 11.5760);
	}
}

function showPosition(lat, lng) {
	var gcenter = {lat: lat, lng: lng};
	if(!gmap) {
		var container = document.getElementById("geoLocation");
		gmap = new google.maps.Map(container, {center: gcenter, zoom: 12 });
	}
	gmap.setCenter(gcenter);
	
	if(!gmarker) {
		gmarker = new google.maps.Marker({position: gcenter, map: gmap});
	}
	gmarker.setPosition(gcenter);
}

function showCityList(array) {
	// regions
	var europeList = document.getElementById("europeList");
	var northAmericaList = document.getElementById("northAmericaList");
	var centralAmericaList = document.getElementById("centralAmericaList");
	var southAmericaList = document.getElementById("southAmericaList");
	var australiaList = document.getElementById("australiaList");
	var asiaList = document.getElementById("asiaList");
	var africaList = document.getElementById("africaList");
	array.forEach(function (location, index) {
		var li = document.createElement("li");
		li.innerHTML = location.name + ", " + location.country;
		li.onclick = function(ev) { getForecast.call(this, location.id, showForecast, showError); };
		
		if("Europe" === location.region) {
			europeList.appendChild(li);
		} else if("North America" === location.region) {
			northAmericaList.appendChild(li);
		} else if("Central America" === location.region) {
			centralAmericaList.appendChild(li);
		} else if("South America" === location.region) {
			southAmericaList.appendChild(li);
		} else if("Australia" === location.region) {
			australiaList.appendChild(li);
		} else if("Asia" === location.region) {
			asiaList.appendChild(li);
		} else if("Africa" === location.region) {
			africaList.appendChild(li);
		}		
	});
}

function toggleRegion(ul) {
	var countryList = ul.firstElementChild;
	if("" === countryList.style.display || "none" === countryList.style.display) {
		countryList.style.display = "block";
	} else {
		countryList.style.display = "none";
	}
}

function doCityClick(input, ev, city) {
	getForecast(city, showForecast, showError);
	return false;
}

function doCitySearch(input, ev) {
	if (13 === ev.keyCode) {
		getForecast(input.value, showForecast, showError);
		return false;
	}
	return true;
}

function showForecast(forecast) {
	// set icon
	var forecastIcon = document.getElementById("forecastIcon");
	if(forecast.weather.icon) {		
		forecastIcon.src = "https://openweathermap.org/img/wn/" + forecast.weather.icon + ".png";
	} else {
		forecastIcon.src = "https://openweathermap.org/img/wn/03d@2x.png";
	}
	
	// set city
	var forecastCity = document.getElementById("forecastCity");
	forecastCity.innerHTML = forecast.name + ",&nbsp;" + forecast.sys.country.toUpperCase() + "&nbsp;";
	forecastCity.innerHTML += "<img src='http://openweathermap.org/images/flags/" + forecast.sys.country.toLowerCase() + ".png'/>";
	
	// set additional info
	var forecastDetails = document.getElementById("forecastDetails");
	forecastDetails.innerHTML = "";
	
	var strong = document.createElement("strong");
	strong.innerHTML = Math.round(forecast.main.temp) + " °C";
	forecastDetails.appendChild(strong);
	var text = document.createTextNode(" with " + forecast.weather.description);
	forecastDetails.appendChild(text);
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Temperature ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.main.temp_min + " °C to " + forecast.main.temp_max + " °C");
	forecastDetails.appendChild(text);
	
	if(forecast.rain) {
		const rain = forecast.rain["1h"] ? forecast.rain["1h"] : forecast.rain["3h"] ? forecast.rain["3h"] : 0;
		strong = document.createElement("strong");
		strong.innerHTML = "</br> Rain ";
		forecastDetails.appendChild(strong);
		text = document.createTextNode(rain + " mm/m²");
		forecastDetails.appendChild(text);
	}
	
	if(forecast.snow) {
		const snow = forecast.snow["1h"] ? forecast.snow["1h"] : forecast.snow["3h"] ? forecast.snow["3h"] : 0;
		strong = document.createElement("strong");
		strong.innerHTML = "</br> Snow ";
		forecastDetails.appendChild(strong);
		text = document.createTextNode(snow + " mm/m²");
		forecastDetails.appendChild(text);
	}	
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Clouds ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.clouds.all + " %");
	forecastDetails.appendChild(text);
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Humidity ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.main.humidity + " %");
	forecastDetails.appendChild(text);
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Wind ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(formatKmph(forecast.wind.speed) + " km/h");
	forecastDetails.appendChild(text);
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Pressure ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.main.pressure + " hpa");
	forecastDetails.appendChild(text);
	
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Sunrise ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(formatTime(forecast.sys.sunrise, forecast.timezone));
	forecastDetails.appendChild(text);
	
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Sunset ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(formatTime(forecast.sys.sunset, forecast.timezone));
	forecastDetails.appendChild(text);
	
	if(forecast.coord) {
		showPosition(forecast.coord.lat, forecast.coord.lon);
	}
}

function showError(httpStatus, response) {
	showToast("<b>" + httpStatus + "</b> " + response.message);
}

function showToast(msg) {
	var x = document.getElementById("toast");
	x.innerHTML = msg;
	x.className = "show";
	setTimeout(function() { 
		x.className = x.className.replace("show", ""); 
		x.innerHTML = "";
	}, 3000);	
}

function formatTime(timestamp, timezone) {
	if(!timezone) {
		timezone = 0;
	}
	const time = new Date(timestamp + timezone * 1000);
	const hours = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	const minutes = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	return hours + ":" + minutes;
}

function formatKmph(value) {
	return (value * 3.6).toFixed(1);
}