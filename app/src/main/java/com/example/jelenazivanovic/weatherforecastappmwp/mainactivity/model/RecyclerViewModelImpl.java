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
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel {

    private RecyclerViewPresenter presenter;
    private DataFromInternet data;
    private Context mContext;
    private DatabaseInsertWeatherInfo weatherInfo;
    private boolean isInserted;


    public RecyclerViewModelImpl(RecyclerViewPresenter presenter, Context context) {
        this.presenter = presenter;
        this.mContext = context;
        this.data = new DataFromInternet();
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

        weatherInfo.isFatabaseEmpty().flatMap(new Func1<Boolean, Observable<List<Weather>>>() {
            @Override
            public Observable<List<Weather>> call(Boolean aBoolean) {
                if (aBoolean) {
                    isInserted = false;
                   return data.getDataFromInternet("MountainView", mContext).subscribeOn(Schedulers.io()) ;
                }
                isInserted = true;
                return weatherInfo.readFromBase();
            }
        }).subscribe(new Subscriber<List<Weather>>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Weather> weatherList) {
                if (!isInserted) {
                    weatherInfo.insertData(weatherList);
                }
                presenter.updateWeather(weatherList);
            }
        });
    }

    }

