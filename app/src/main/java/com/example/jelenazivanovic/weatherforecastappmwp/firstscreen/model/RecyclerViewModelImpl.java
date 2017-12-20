package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.model;

import com.example.jelenazivanovic.weatherforecastappmwp.MainActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.WeatherObj;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter.RecyclerViewPresenter;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel {

    private RecyclerViewPresenter presenter;

    public RecyclerViewModelImpl(RecyclerViewPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void getWeatherResults() {
        ArrayList<WeatherObj> dummyWeatherData = new ArrayList<>();
        dummyWeatherData.add(new WeatherObj("Today, May 17", "Clear", "17°C", "15°C"));
        dummyWeatherData.add(new WeatherObj("Tomorrow", "Cloudy", "19°C", "15°C"));
        dummyWeatherData.add(new WeatherObj("Thursday", "Rainy", "30°C", "11°C"));
        dummyWeatherData.add(new WeatherObj("Friday", "Thunderstorms", "21°C", "9°C"));
        dummyWeatherData.add(new WeatherObj("Saturday", "Thunderstorms", "16°C", "7°C"));

        presenter.updateWeatherInfo(dummyWeatherData);

    }
}
