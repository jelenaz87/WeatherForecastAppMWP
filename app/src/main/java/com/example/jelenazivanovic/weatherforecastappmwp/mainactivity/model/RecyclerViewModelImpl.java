package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel, IsResponseSuccesfull{

    private RecyclerViewPresenter presenter;
    private DataFromInternet data;



    public RecyclerViewModelImpl(RecyclerViewPresenter presenter) {
        this.presenter = presenter;
        this.data = new DataFromInternet(this);

    }


    @Override
    public void getWeatherResults() {
        data.getDataFromInternet("Belgrade");

    }

    @Override
    public void getResponse(Object data) {
        presenter.updateWeather(data);
    }
}
