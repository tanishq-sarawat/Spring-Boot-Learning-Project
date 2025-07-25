package com.example.demo.service;

import com.example.demo.api.response.Appcache;
import com.example.demo.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${api}")


    private  String api_key;
   // private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Appcache app;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse1 = redisService.get( city, WeatherResponse.class);
        if(weatherResponse1 != null){
            return weatherResponse1;
        }
        else{
            String Final = app.appcache.get("weather_api").replace("<city>", city).replace("<api_key>", api_key);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(Final, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse weatherResponse = response.getBody();
            redisService.set("weather_api:" + city, weatherResponse, 300);
            System.out.println(weatherResponse);
            return weatherResponse;
        }



   
    }
}
