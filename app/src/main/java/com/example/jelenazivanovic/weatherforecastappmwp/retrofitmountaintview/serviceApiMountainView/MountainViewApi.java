package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jelena.zivanovic on 12/26/2017.
 */

public interface MountainViewApi {

    @GET("staticweather?q=94043%2CUSA&mode=json&units=metric&cnt=14")
    Call<WeatherMountainView> getWeather ();
}
