package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public interface RecyclerViewPresenter {

    void invokePresenter ();
    void updateWeatherInfo (WeatherObject weatherObject);

}
