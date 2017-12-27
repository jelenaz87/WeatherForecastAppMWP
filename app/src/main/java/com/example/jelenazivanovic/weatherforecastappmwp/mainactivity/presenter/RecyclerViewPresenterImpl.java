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
        this.model = new RecyclerViewModelImpl(this, context);
    }
    @Override
    public void invokePresenter() {
        model.getWeatherResults();

    }

    @Override
    public void updateWeather(List<Weather> mList) {

            view.lisOfWeather(mList);


    }


}
