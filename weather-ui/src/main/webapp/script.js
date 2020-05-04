// global variables
var gmap;
var gmarker;

// onload
window.onload = function() {
	getCities(showCityList, showError);
}

function getPosition(ev) {
	if (ev) {
		ev.stopPropagation();	
	}

	if (navigator.geolocation) { // html 5
		navigator.geolocation.getCurrentPosition(function(position) { // success
			console.log("User geolocation latitude=" + position.coords.latitude + " and longitude=" + position.coords.longitude);
			getForecastByPosition(position.coords.latitude, position.coords.longitude, showForecast, showError);
			showPosition(position.coords.latitude, position.coords.longitude);
			
		}, function() { // error
			getDefaultPosition();
			
		}, {timeout: 5 * 1000});
	} else {
		console.log("Geolocation is not supported or is turned off in this browser.");
		getDefaultPosition();
	}
}

function getDefaultPosition() {
	console.log("Default geolocation downtown Munich DE, latitude=48.1365, longitude=11.5760");
	getForecastByPosition(48.1365, 11.5760, showForecast, showError);
	showPosition(48.1365, 11.5760);
}

function showPosition(lat, lng) {
	var gcenter = {lat: lat, lng: lng};
	if(!gmap) {
		var container = document.getElementById("gmapDiv");
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
		li.onclick = function(ev) {
			ev.stopPropagation(); 
			getForecast.call(this, location.id, null, null, showForecast, showError); 
		};
		
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
	if (countryList.style.display === "" || countryList.style.display === "none") {
		countryList.style.display = "block";
	} else {
		countryList.style.display = "none";
	}
}

function doCityClick(ev, city) {
	ev.stopPropagation();
	getForecast(city, null, null, showForecast, showError);
}

function doCitySearch(input, ev) {
	ev.stopPropagation();
	if (13 === ev.keyCode) { // enter
		if (input.value && input.value.length > 2) {
			if (isZipcode(input.value)) {
				getForecast(null, null, input.value, showForecast, showError);
			} else {
				getForecast(null, input.value, null, showForecast, showError);
			}
		}
	}
}

function showForecast(forecast) {
	var infoContainer = document.getElementById('weatherInfo');
	infoContainer.style.display = 'block';
	infoContainer.innerHTML = "";

	// set city
	var forecastCity = document.createElement('H3');
	infoContainer.appendChild(forecastCity);
	forecastCity.innerHTML = forecast.name + ",&nbsp;" + forecast.sys.country.toUpperCase() + "&nbsp;";
	var countryFlag = document.createElement('IMG');
	countryFlag.src = "http://openweathermap.org/images/flags/" + forecast.sys.country.toLowerCase() + ".png";
	countryFlag.width = '26';
	countryFlag.height = '16';
	countryFlag.alt = "flag";
	forecastCity.appendChild(countryFlag);
	
	// set temperature
	var forecastTemp = document.createElement('SPAN');
	infoContainer.appendChild(forecastTemp);
	var tempSpan = document.createElement('SPAN');
	tempSpan.className = 'badge';
	tempSpan.innerHTML = Math.round(forecast.main.temp) + " °C";
	forecastTemp.appendChild(tempSpan);
	var text = document.createTextNode(" with " + forecast.weather.description);
	forecastTemp.appendChild(text);

	// set additional details, like temperature variation, rain, snow, clouds and wind speed
	var forecastDetails = document.createElement('P');
	infoContainer.appendChild(forecastDetails);

	// set temperature variation
	var strong = document.createElement("strong");
	strong.innerHTML = "Temperature ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.main.temp_min + " °C to " + forecast.main.temp_max + " °C");
	forecastDetails.appendChild(text);
	
	// set weather icon
	if(forecast.weather.icon) {		
		var forecastIcon = document.createElement('IMG');
		forecastIcon.className = 'forecastIcon';
		forecastIcon.src = "https://openweathermap.org/img/wn/" + forecast.weather.icon + ".png";
		// forecastIcon.src = "https://openweathermap.org/img/wn/03d@2x.png";
		forecastIcon.alt = "weather";
		infoContainer.appendChild(forecastIcon);
	} 

	// set rain
	if(forecast.rain) {
		const rain = forecast.rain["1h"] ? forecast.rain["1h"] : forecast.rain["3h"] ? forecast.rain["3h"] : 0;
		strong = document.createElement("strong");
		strong.innerHTML = "</br> Rain ";
		forecastDetails.appendChild(strong);
		text = document.createTextNode(rain + " mm/m²");
		forecastDetails.appendChild(text);
	}
	
	// set snow
	if(forecast.snow) {
		const snow = forecast.snow["1h"] ? forecast.snow["1h"] : forecast.snow["3h"] ? forecast.snow["3h"] : 0;
		strong = document.createElement("strong");
		strong.innerHTML = "</br> Snow ";
		forecastDetails.appendChild(strong);
		text = document.createTextNode(snow + " mm/m²");
		forecastDetails.appendChild(text);
	}	
	
	// set clouds
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Clouds ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(forecast.clouds.all + " %");
	forecastDetails.appendChild(text);

	// set wind
	strong = document.createElement("strong");
	strong.innerHTML = "</br> Wind ";
	forecastDetails.appendChild(strong);
	text = document.createTextNode(formatKmph(forecast.wind.speed) + " km/h");
	forecastDetails.appendChild(text);
	
	// set addition details lie humidity, pressure, sunrise and sunset times
	var forecastSubdetails = document.createElement('P');
	infoContainer.appendChild(forecastSubdetails);

	// set humidity and pressure
	text = document.createTextNode("Humidity " + forecast.main.humidity + " %");
	text.appendData(" , Pressure " + forecast.main.pressure + " hpa");
	forecastSubdetails.appendChild(text);
	forecastSubdetails.appendChild(document.createElement('BR'));
	
	// set sunrise and sunset
	text = document.createTextNode("Sunrise " + formatTime(forecast.sys.sunrise));
	text.appendData(" , Sunset " + formatTime(forecast.sys.sunset));
	forecastSubdetails.appendChild(text);
	
	if(forecast.coord) {
		showPosition(forecast.coord.lat, forecast.coord.lon);
	}
}

function showError(status, message) {
	showToast("<b>" + status + "</b> " + message);
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

function formatTime(timestamp) {
	var time = new Date(timestamp * 1000);
	var hours = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var minutes = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	return hours + ":" + minutes;
}

function formatKmph(value) {
	return (value * 3.6).toFixed(1);
}

/* Return true if the string contains a digit */
function isZipcode(str) {
	for (var i = 0; i < str.length; i++) {
		var ch = str.charAt(i);
		if (ch >= '0' && ch <= '9') {
			return true;
		}
	}
	return false;
}