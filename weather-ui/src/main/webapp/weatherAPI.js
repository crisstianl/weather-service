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
			success(JSON.parse(this.responseText));
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log("Server responsed with " + this.status + " " + this.responseText);
			if (this.responseText && this.responseText.length > 0) {
				var response = JSON.parse(this.responseText)
				error(this.status, response.message);
			} else {
				error(this.status, "server error");
			}
		}
	};

	doGet(url, callback);
}

function getForecast(city, query, zipcode, success, error) {
	var url = null;
	if (city) {
		url = ENDPOINT + "/forecast/" + city;
	} else if (query) { 
		url = ENDPOINT + "/forecast?q=" + query;
	} else if (zipcode) {
		url = ENDPOINT + "/forecast?code=" + zipcode;
	}
	var callback = function() {
		if (this.readyState == 4 && this.status == 200) { // success
			success(JSON.parse(this.responseText));
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log("Server responsed with " + this.status + " " + this.responseText);
			if (this.responseText && this.responseText.length > 0) {
				var response = JSON.parse(this.responseText)
				error(this.status, response.message);
			} else {
				error(this.status, "server error");
			}
		}
	};

	doGet(url, callback);
}

function getForecastByPosition(lat, lon, success, error) {
	var url = ENDPOINT + "/forecast" + "?lat=" + lat + "&lon=" + lon;
	var callback = function() {
		if (this.readyState == 4 && this.status == 200) { // success
			success(JSON.parse(this.responseText));
		} else if (this.readyState == 4 && this.status != 200) { // error
			console.log("Server responsed with " + this.status + " " + this.responseText);
			if (this.responseText && this.responseText.length > 0) {
				var response = JSON.parse(this.responseText)
				error(this.status, response.message);
			} else {
				error(this.status, "server error");
			}
		}
	};

	doGet(url, callback);
}