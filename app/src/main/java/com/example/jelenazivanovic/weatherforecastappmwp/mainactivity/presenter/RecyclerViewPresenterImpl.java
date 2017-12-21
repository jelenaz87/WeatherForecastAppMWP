package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceScreen;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.WeatherObj;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewPresenterImpl implements RecyclerViewPresenter {

    private RecyclerViewView view;
    private RecyclerViewModel model;
    private Context mContext;


    public RecyclerViewPresenterImpl(RecyclerViewView view, ServiceApi mServiceApi, Context context) {
        this.view = view;
        this.model = new RecyclerViewModelImpl(this, mServiceApi);
        this.mContext = context;
    }
    @Override
    public void invokePresenter() {
        model.getWeatherResults();

    }

    @Override
    public void updateWeatherInfo(WeatherObject weatherObject) {
        int y=0;
        long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
        ArrayList<WeatherObj> list = new ArrayList<>();

        for (int i=0; i<weatherObject.getList().size(); i++) {

          long dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * y;
          String dateString= SunshineDateUtils.getFriendlyDateString(mContext, dateTimeMillis, false);
          int weatherId = weatherObject.getList().get(i).getWeatherDetail().get(0).getWeatherId();
          String description = SunshineWeatherUtils.getStringForWeatherCondition(mContext, weatherId);

          double tempMinInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMinTemperature();
          double tempMinInCelsius = tempMinInFahrenheit -273.15;
          String minTemp =  SunshineWeatherUtils.formatTemperature(mContext, tempMinInCelsius);

          double tempMaxInFahrenheit = weatherObject.getList().get(i).getTemperatureObject().getMaxTemperature();
          double tempMaxInCelsius = tempMaxInFahrenheit -273.15;
          String maxTemp =  SunshineWeatherUtils.formatTemperature(mContext, tempMaxInCelsius);

          list.add(new WeatherObj(dateString, description,minTemp,maxTemp, weatherId));

         y++;
         i= i+7;
        }

        view.lisOfWeather(list);
    }
}
