package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import java.util.List;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public interface RecyclerViewPresenter {

    void invokePresenter ();
    void updateWeather (List<Weather> mList);
    void sendIdOfRow (int id);
    void getWeatherObject (Weather weather);
    void checkStateOfDatabase ();
    void updateUi (List<Weather> mList);

}
