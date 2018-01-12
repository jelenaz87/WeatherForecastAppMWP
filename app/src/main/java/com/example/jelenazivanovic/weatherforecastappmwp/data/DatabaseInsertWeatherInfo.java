package com.example.jelenazivanovic.weatherforecastappmwp.data;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by jelena.zivanovic on 12/26/2017.
 */

public class DatabaseInsertWeatherInfo {

    public static List<Long> list_inserted_row;
    private WeatherDatabase database;
    private Context mContext;



    public DatabaseInsertWeatherInfo() {

    }

    public DatabaseInsertWeatherInfo(Context mContext) {
        this.mContext = mContext;
        this.database = WeatherDatabase.getWeatherDatabaseInstance(mContext);

    }

    public Observable<Boolean> isFatabaseEmpty () {

      return Observable.create(new ObservableOnSubscribe<Boolean>() {
          @Override
          public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
              e.onNext(readFromBase().isEmpty().blockingGet());

          }
      });
    }

    public List<Weather> readDatabaseWeatherInfo() {

        return database.weatherDao().loadAllWeatherObject();

    }

    public Observable<List<Weather>> readFromBase () {
        return Observable.fromArray(database.weatherDao().loadAllWeatherObject());
    }

    public Observable<Weather> readOneRowFromDatabase (final int id) {
       return Observable.create(new ObservableOnSubscribe<Weather>() {
            @Override
            public void subscribe(ObservableEmitter<Weather> e) throws Exception {
                e.onNext(WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().loadAEqualWithAnId(id));
            }
        });
    }

    public void insertData(List<Weather> weatherList) {

        for (int i = 0; i < weatherList.size(); i++) {
           list_inserted_row = database.weatherDao().insertWeatherObject(weatherList.get(i));
        }
    }

    public Flowable<List<Weather>> getDataIfChangesExistInDatabase () {
        return WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().getFlowableListOfObject();
    }
}



