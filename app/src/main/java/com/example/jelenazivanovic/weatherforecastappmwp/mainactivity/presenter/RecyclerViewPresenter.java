package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public interface RecyclerViewPresenter {

    void invokePresenter ();
    void updateWeatherInfo (WeatherObject weatherObject);
    void updateWeather (WeatherMountainView object);

}
