package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
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
        this.model = new RecyclerViewModelImpl(this);
    }
    @Override
    public void invokePresenter() {
        model.getWeatherResults();

    }

    @Override
    public void updateWeather(Object object) {
        DatabaseInsertWeatherInfo weatherInfo = new DatabaseInsertWeatherInfo(mContext);
        List<Weather> list = weatherInfo.readDatabaseWeatherInfo();

        if (list.size() == 0 || list.isEmpty()) {
            weatherInfo.insertData(object);
            list =weatherInfo.readDatabaseWeatherInfo();
        }

            view.lisOfWeather(list);


    }


}
