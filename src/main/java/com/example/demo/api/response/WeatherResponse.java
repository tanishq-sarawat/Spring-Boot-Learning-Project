package com.example.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class WeatherResponse {
    @Getter
    @Setter
    private Current current;
    @Getter
    @Setter
    public class Current {
        @JsonProperty("observation_time")
        private String observationtTime;
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherdescriptions;
        @JsonProperty("is_day")
        private String day;
        private int feelslike;
    }
}






