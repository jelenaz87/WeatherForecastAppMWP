package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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
        data.getDataFromInternet("MountainView", mContext).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Weather>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Weather> weatherList) {
                weatherInfo.insertData(weatherList);
                List<Weather> list = weatherInfo.readDatabaseWeatherInfo();
                presenter.updateWeather(list);

            }
        });

    }



    @Override
    public void getResponse(Observable<List<Weather>> datam) {

//        List<Weather> list = weatherInfo.readDatabaseWeatherInfo();
//
//        if (list.size() == 0 || list.isEmpty()) {
//
//            weatherInfo.insertData(data);
//            list = weatherInfo.readDatabaseWeatherInfo();
//        }
//        presenter.updateWeather(list);
    }
}
