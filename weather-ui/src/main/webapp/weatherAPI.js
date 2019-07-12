/* CONSTANTS */
const ENDPOINT = "http://localhost:8080/weather-service/v1";
const AUTH_KEY = "YWRtaW46cGVyZmVjdHdlYXRoZXI=";

function doGet(url, callback) {
	console.log("GET " + url);
	var xhttp = new XMLHttpRequest();
	xhttp.timeout = 5 * 1000;
	xhttp.onreadystatechange = callback;
	xhttp.open("GET", url, true);
	xhttp.setRequestHeader("Authorization", "Basic " + AUTH_KEY);
	xhttp.send();
}

function doPost(url, payload, callback) {
	console.log("POST " + url);
	var xhttp = new XMLHttpRequest();
	xhttp.timeout = 5 * 1000;
	xhttp.onreadystatechange = callback;
	xhttp.open("POST", url, true);
	xhttp.setRequestHeader("Authorization", "Basic " + AUTH_KEY);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(payload);
}

function getCities(success, error) {
	var url = ENDPOINT + "/cities/all"
	var callback = function() {
		if (this.readyState == 4 && this.status == 200) { // success
			const response = JSON.parse(this.responseText);
			success(response);
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log(this.responseText);
			const response = JSON.parse(this.responseText);
			error(this.status, response);
		}
	};

	doGet(url, callback);
}

function getForecast(city, success, error) {
	var url = ENDPOINT + "/forecast/" + city;
	var callback = function() {
		if (this.readyState == 4 && this.status == 200) { // success
			const forecast = JSON.parse(this.responseText);
			success(forecast);
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log(this.responseText);
			const response = JSON.parse(this.responseText);
			error(this.status, response);
		}
	};

	doGet(url, callback);
}

function getForecastByPosition(lat, lon, success, error) {
	var url = ENDPOINT + "/forecast" + "?lat" + lat + "&lon=" + lon;
	var callback = function() {
		if (this.readyState == 4 && this.status == 200) { // success
			const forecast = JSON.parse(this.responseText);
			success(forecast);
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log(this.responseText);
			const response = JSON.parse(this.responseText);
			error(this.status, response);
		}
	};

	doGet(url, callback);
}