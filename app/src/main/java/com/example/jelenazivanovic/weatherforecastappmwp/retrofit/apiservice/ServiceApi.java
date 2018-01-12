package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by jelena.zivanovic on 12/21/2017.
 */

public interface ServiceApi {



    @GET ("forecast")
    Observable<WeatherObject> getWeatherObject(@Query("q") String city, @Query("appid") String apiKey );






    // Call<WeatherMountainView> getWeatherObject(@Query(NetworkUtils.QUERY_PARAM) String location,
    //                                     @Query(NetworkUtils.FORMAT_PARAM) String format,
    //                     @Query(NetworkUtils.UNITS_PARAM) String units,
    //                     @Query(NetworkUtils.DAYS_PARAM) String days);
}
