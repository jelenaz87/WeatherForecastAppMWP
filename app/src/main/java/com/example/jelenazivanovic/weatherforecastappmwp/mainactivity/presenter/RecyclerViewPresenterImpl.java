package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.SunshinePreferences;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;

import java.util.List;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewPresenterImpl implements RecyclerViewPresenter{

    private RecyclerViewView view;
    private RecyclerViewModel model;
    private Context mContext;


    public RecyclerViewPresenterImpl(RecyclerViewView view, Context context) {
        this.view = view;
        this.mContext = context;
        this.model = new RecyclerViewModelImpl(this, context);
    }
    //this is for testing purpose only
    public void setModel(RecyclerViewModel model) {
        this.model = model;
    }

    @Override
    public void invokePresenter(String city) {
        model.getWeatherResults(city);
    }

    @Override
    public void updateWeather(List<Weather> mList) {
            view.lisOfWeather(mList);
    }

    @Override
    public void sendIdOfRow(int id) {
        model.sendIdOfRow(id);
    }

    @Override
    public void getWeatherObject(Weather weather) {
       view.getWeatherFromOneRow(weather);
    }

    @Override
    public void checkStateOfDatabase() {
        model.getStateOfdatabase();
    }

    @Override
    public void updateUi(List<Weather> mList) {
        if (SunshinePreferences.getUnitsFromSharedPreference(mContext).equalsIgnoreCase(mContext.getResources().getString(R.string.pref_units_imperial))) {
            for (int i = 0; i < mList.size(); i++) {
                double tempMax = mList.get(i).getCityObject().getMaxTemperature() + 273.15;
                mList.get(i).getCityObject().setMaxTemperature(tempMax);
                double tempMin = mList.get(i).getCityObject().getMinTemperature() + 273.15;
                mList.get(i).getCityObject().setMinTemperature(tempMin);

            }
            view.updateUi(mList);
        } else {
            view.updateUi(mList);
        }
    }



}
