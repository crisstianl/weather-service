"weather-service" is web application developed in Java that returns accurate weather information from your location using best forecast services from OpenWeatherMap API and location services from Google Map API.

"weather-ui" is web interface developed only in Html that queries the backend (via Ajax) for city names, postalcodes and even geolocation coordinates, afterwhich prints the results in a nice, modern, interactive page. 

Instalation:  
- install any Java application server (Tomcat 8.5+, Glassfish 5+, Wildfly 12+, Weblogic 10+, etc).
- deploy weather-service.war from weather-service/target into your server's deployment directory or domain.
- deploy weather-ui.war from weather-ui/target into your server's deployment directory or domain.
- go to http://localhost:8080/weather-ui to see the web interface.
- go to http://localhost:8080/weather-service/swagger-ui.html to preview and try application endpoints.
- go to http://localhost:8080/weather-service/ to browse endpoints using Hal browser.
  
       
Usage:   
In the top section you will be presented with quicklinks to most popular metropolis in Europe, select one.  
For convience the application also maps the most important cities on Europe, Asia, America, Africa and Australia.  
On the left in the menu section click on the location icon to view forecast in your position (i.e geolocation services must be enabled in your browser).  
Also on the left there is a search bar, you can type either a city name or the postal code and press enter. If your query got matched in the backend, then you should see on the center screen three pieces of information, otherwise an error message.  
1. the queried city, the parent country and the weather state represented through an icon (sunny, rainy, cloudy, misty, etc).
2. comprehensive weather parameters, temperature in Celsius, cloud coverage, rain drop, wind speed, atmospheric pressure, and other.
3. location on google map.


Frameworks and libraries:
- java standard edition 8
- java enterprise edition 7
- spring-boot-starter-web 2.0
- spring-boot-starter-actuator 2.0
- spring-boot-starter-security 2.0
- spring-data-rest-hal-browser 3.0
- spring-boot-devtools 2.0
- spring-boot-starter-test 2.0
- spring-cloud-contract-wiremock 2.0
- jackson-dataformat-xml 2.9
- springfox-swagger2 2.4
- springfox-swagger-ui 2.5
- mockito 2.1
- junit 4.1
- hamcrest 1.3
