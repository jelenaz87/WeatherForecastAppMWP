package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by jelena.zivanovic on 12/26/2017.
 */

public class DatabaseInsertWeatherInfo {

    public static List<Long> sInitialized;
    private WeatherDatabase database;
    private Context mContext;
    private ArrayList<Weather> mList;
    private long dateTimeMillis;

    public DatabaseInsertWeatherInfo() {

    }

    public DatabaseInsertWeatherInfo(Context mContext) {
        this.mContext = mContext;
        this.database = WeatherDatabase.getWeatherDatabaseInstance(mContext);
        this.mList = new ArrayList<>();
    }

    public Observable<Boolean> isFatabaseEmpty () {

      return Observable.create(new Observable.OnSubscribe<Boolean>() {
          @Override
          public void call(Subscriber<? super Boolean> subscriber) {
              try {
                  subscriber.onNext(database.weatherDao().loadAllWeatherObject().isEmpty());
                  subscriber.onCompleted();     // Signal about the completion subscriber
              } catch (Exception e) {
                  subscriber.onError(e);        // Signal about the error to subscriber
              }
          }
      });
    }

    public List<Weather> readDatabaseWeatherInfo() {

        return database.weatherDao().loadAllWeatherObject();

    }

    public Observable<List<Weather>> readFromBase () {
        return Observable.from(database.weatherDao().loadAllWeatherObject()).toList();
    }

    public void insertData(List<Weather> weatherList) {

        for (int i = 0; i < weatherList.size(); i++) {
            database.weatherDao().insertWeatherObject(weatherList.get(i));
        }
    }
}



