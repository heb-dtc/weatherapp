package com.heb.eguzkia.network;

import retrofit.RestAdapter;

public class RestClient {

    private static WeatherApi REST_CLIENT;
    private static String ROOT =
            "http://api.openweathermap.org/data/2.5";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static WeatherApi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .build();

        REST_CLIENT = restAdapter.create(WeatherApi.class);
    }
}
