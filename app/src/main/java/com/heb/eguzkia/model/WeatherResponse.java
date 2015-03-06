package com.heb.eguzkia.model;

import java.util.List;

public class WeatherResponse {
    int cod;
    private String base;
    private List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }
}
