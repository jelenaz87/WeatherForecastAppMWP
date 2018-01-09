package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;

import java.util.List;

/**
 * Created by jelena.zivanovic on 1/9/2018.
 */

public class ListAdapterAsyncTaskLoader extends AsyncTaskLoader<List<Weather>> {

    public ListAdapterAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public List<Weather> loadInBackground() {
        return WeatherDatabase.getWeatherDatabaseInstance(getContext()).weatherDao().loadAllWeatherObject();
    }
}
