package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter;

import com.example.jelenazivanovic.weatherforecastappmwp.MainActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.WeatherObj;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.model.RecyclerViewModel;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.model.RecyclerViewModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.view.RecyclerViewView;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewPresenterImpl implements RecyclerViewPresenter {

    private RecyclerViewView view;
    private RecyclerViewModel model;


    public RecyclerViewPresenterImpl(RecyclerViewView view) {
        this.view = view;
        this.model = new RecyclerViewModelImpl(this);
    }
    @Override
    public void invokePresenter() {
        model.getWeatherResults();

    }

    @Override
    public void updateWeatherInfo(ArrayList<WeatherObj> mList) {

        view.lisOfWeather(mList);
    }
}
