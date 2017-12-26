package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewPresenterImpl implements RecyclerViewPresenter {

    private RecyclerViewView view;
    private RecyclerViewModel model;
    private Context mContext;


    public RecyclerViewPresenterImpl(RecyclerViewView view, MountainViewApi api, Context context) {
        this.view = view;
        this.mContext = context;
        this.model = new RecyclerViewModelImpl(this, api,context);
    }
    @Override
    public void invokePresenter() {
        model.getWeatherResults();

    }

    @Override
    public void updateWeatherInfo(WeatherObject weatherObject) {


        DatabaseInsertWeatherInfo weatherInfo = new DatabaseInsertWeatherInfo(mContext);
        List<Weather> list =weatherInfo.readDatabaseWeatherInfo();

        view.lisOfWeather(list);
      //  ArrayList<Weather> lista = new ArrayList<>(Arrays.asList(weatherInfo.readDatabaseWeatherInfo()));
      //  view.lisOfWeather(list);
    }

    @Override
    public void updateWeather(WeatherMountainView object) {
        DatabaseInsertWeatherInfo weatherInfo = new DatabaseInsertWeatherInfo(mContext);
        weatherInfo.insertData(object);
        List<Weather> list =weatherInfo.readDatabaseWeatherInfo();

        view.lisOfWeather(list);
    }
}
