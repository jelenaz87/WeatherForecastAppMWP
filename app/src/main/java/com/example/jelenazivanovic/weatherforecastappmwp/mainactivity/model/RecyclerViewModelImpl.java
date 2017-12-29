package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;

import java.util.List;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel, IsResponseSuccesfull{

    private RecyclerViewPresenter presenter;
    private DataFromInternet data;
    private Context mContext;
    private DatabaseInsertWeatherInfo weatherInfo;



    public RecyclerViewModelImpl(RecyclerViewPresenter presenter, Context context) {
        this.presenter = presenter;
        this.mContext = context;
        this.data = new DataFromInternet(this);
        this.weatherInfo = new DatabaseInsertWeatherInfo(mContext);

    }

    //this for testing purpose only
    public void setData(DataFromInternet data) {
        this.data = data;
    }
    //this for testing purpose only
    public void setWeatherInfo(DatabaseInsertWeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    @Override
    public void getWeatherResults() {
        data.getDataFromInternet("Belgrade");
    }

    @Override
    public void getResponse(Object data) {
        List<Weather> list = weatherInfo.readDatabaseWeatherInfo();

        if (list.size() == 0 || list.isEmpty()) {
            weatherInfo.insertData(data);
            list = weatherInfo.readDatabaseWeatherInfo();
        }
        presenter.updateWeather(list);
    }
}
