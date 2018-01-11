package com.example.jelenazivanovic.weatherforecastappmwp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;

import java.util.List;

/**
 * Created by jelena.zivanovic on 1/11/2018.
 */

public class RecyclerViewModel extends AndroidViewModel {

    private final LiveData<List<Weather>> listLiveData;
    private WeatherDatabase database;

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
        database = WeatherDatabase.getWeatherDatabaseInstance(this.getApplication());
        this.listLiveData = database.weatherDao().loadAllLoadData();
    }

    public LiveData<List<Weather>> getItemAndPersonList() {

        return listLiveData;

    }

}
