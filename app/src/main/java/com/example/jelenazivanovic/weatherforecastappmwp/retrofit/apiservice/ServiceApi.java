package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jelena.zivanovic on 12/21/2017.
 */

public interface ServiceApi {


    @GET("forecast?id=792680&appid=88679d8457078f98d550b4e24ac9ac26")
    Observable<WeatherObject> getWeatherObject();




    // Call<WeatherMountainView> getWeatherObject(@Query(NetworkUtils.QUERY_PARAM) String location,
    //                                     @Query(NetworkUtils.FORMAT_PARAM) String format,
    //                     @Query(NetworkUtils.UNITS_PARAM) String units,
    //                     @Query(NetworkUtils.DAYS_PARAM) String days);
}
