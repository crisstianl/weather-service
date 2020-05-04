### Openweather
Enterprise application composed by a Html module that handles user inputs and makes forecast requests to a backend API.  
The backend module interprets the input parameters and forwards the request to an external weather provider, in this case OpenWeatherMap.  

**weather-ui** module is the application web interface developed in Html and Javascript that helps the user select a location or search for one. The users can submit city names, postal codes or even geolocation coordinates (latitude and longitude) to the backend. With the help of Ajax the user can submit these parameters asynchronously but also have the response rendered silently in the background. The response contains the forecast temperature, description, wind speed, humidity, clouds concentration and a geographical map of the location.  

**weather-service** module is the application backend APIs developed in SpringBoot and Java that acts as a broker between the web interface and the weather provider. The module exposes the following endpoints:  
- v1/hello, accepts no input and produces hello world.  
- v1/ping, accepts no input and produces a localized hello world.  
- v1/cities/{region}, accepts "all", "europe", "asia", "africa", "australia", "north_america", "central_america", "south_america" and produces a json array containing all the major metropolis for the specified region.  
- v1/forecast/{id}, accepts a key that helps identiy a city or location quickly and produces a json message with forecast information.  
- v1/forecast?q={city_name,country}, accepts a string containing the city localized name (e.g. Müunchen) and the country iso-alpha 2 code (e.g. DE) and produces a json message with the forecast information.
- v1/forecast?code={postal_code}, accepts a valid US postal code (e.g. 10001) and produces a json message with the forecast information.
- v1/forecast?lat={latitude}&lon={longitude}, accepts 2 decimal values that pinpoint a position in a city and produces a json message with the forecast information.  
- v1/forecast, post a json message containing forecast information to be saved in the database and produces a URL to the city name. 
- v1/forecast, put a json message containing forecast information to be saved in the database and produces a URL to the city name.
- v1/forecast/{city}, delete forecast information from the database using a city name or id.

### Instalation
- install any Java application server (Tomcat 8.5+, Glassfish 5+, Wildfly 12+, Weblogic 10+, etc).
- deploy **weather-service.war** from "weather-service/target" into your server's deployment directory or domain.
- deploy **weather-ui.war** from "weather-ui/target" into your server's deployment directory or domain.
- go to http://localhost:8080/weather-ui to access the web interface.
- go to http://localhost:8080/weather-service/swagger-ui.html to preview and try application endpoints.
- go to http://localhost:8080/weather-service/ to browse endpoints using Hal browser.
       
### Usage
When you open **weather-ui** the application will try to read your location from the browser using Html 5 Geolocation API (you must have location access enabled in your browser). Using this information the application will fetch the weather for your position.  
In the top section of the page you have quicklinks to the most popular metropolises in Europe, click to view weather.      
In the left section of the page you have the following regions: Europe, Asia, America, Africa and Australia. Click on a region to access the metropolises in that region, then click on the city to view weather.        
In the left section there is a search bar, type a city name in the native language (e.g. München) and optionally the country in iso-alpha 2 format (e.g. DE), or type a US postal code and then press enter.   
If your query got matched in the backend, then you should see in the center screen three pieces of information:
1. the queried city together with the parent country and a graphical icon representing the weather state (sunny, rainy, cloudy, misty, etc).
2. comprehensive weather parameters, temperature in Celsius, clouds coverage, rain/snow drop, wind speed, atmospheric pressure, and other.
3. location of the city on google map.

### External APIs
- OpenWeatherMAP API
- Geolocation API
- Google Map

### Frameworks and libraries
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
