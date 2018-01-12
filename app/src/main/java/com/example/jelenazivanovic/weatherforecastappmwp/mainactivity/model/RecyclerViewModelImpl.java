package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.SunshinePreferences;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;


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

        weatherInfo.isFatabaseEmpty().flatMap(new Function<Boolean, Observable<List<Weather>>>() {
            @Override
            public Observable<List<Weather>> apply(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
              //      isInserted = false;
                    return data.getDataFromInternet(SunshinePreferences.getWeatherLocation(mContext), mContext).subscribeOn(Schedulers.io());
                }
                isInserted = true;
                return data.getDataFromInternet(SunshinePreferences.getWeatherLocation(mContext),mContext).subscribeOn(Schedulers.io());
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Weather>>() {
            @Override
            public void accept(List<Weather> weatherList) throws Exception {
            //    if (!isInserted) {
                    weatherInfo.insertData(weatherList);
 //}
                presenter.updateWeather(weatherList);
            }
            } );
                
    }

    @Override
    public void sendIdOfRow(final int id) {
       weatherInfo.readOneRowFromDatabase(id).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Weather>() {
           @Override
           public void accept(Weather weather) throws Exception {
               presenter.getWeatherObject(weather);
           }
       });

    }

    @Override
    public void getStateOfdatabase() {

        DatabaseInsertWeatherInfo data = new DatabaseInsertWeatherInfo(mContext);
        data.getDataIfChangesExistInDatabase().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new FlowableSubscriber<List<Weather>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<Weather> weatherList) {
                presenter.updateUi(weatherList);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(List<Weather> weatherList) {
//                if (!isInserted) {
//                    weatherInfo.insertData(weatherList);
//                }
//                presenter.updateWeather(weatherList);
//            }
}

