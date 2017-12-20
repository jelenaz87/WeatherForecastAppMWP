package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter;

import com.example.jelenazivanovic.weatherforecastappmwp.MainActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.WeatherObj;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public interface RecyclerViewPresenter {

    void invokePresenter ();
    void updateWeatherInfo (ArrayList<WeatherObj> mList);

}
