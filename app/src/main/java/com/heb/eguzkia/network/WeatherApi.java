package com.heb.eguzkia.network;

import com.heb.eguzkia.model.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherApi {
    @GET("/weather")
    void getWeather(@Query("q") String city,
                    Callback<WeatherResponse> callback);
}
