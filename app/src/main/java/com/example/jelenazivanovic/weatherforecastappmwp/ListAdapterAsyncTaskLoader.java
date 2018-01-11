package com.example.jelenazivanovic.weatherforecastappmwp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.preference.PreferenceManager;

import com.example.jelenazivanovic.weatherforecastappmwp.data.DatabaseInsertWeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.data.SunshinePreferences;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDao;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by jelena.zivanovic on 1/9/2018.
 */

public class ListAdapterAsyncTaskLoader extends AsyncTaskLoader<List<Weather>> {


   private List<Weather> mData;
    private WeatherDatabase database;
    private Context mContext;
    DatabaseInsertWeatherInfo insertWeatherInfo;
    boolean aBoolean = false;


    public ListAdapterAsyncTaskLoader(Context context) {
        super(context);
        this.mContext = context;
        insertWeatherInfo = new DatabaseInsertWeatherInfo(context);
        onContentChanged();
       mData = insertWeatherInfo.readDatabaseWeatherInfo();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mData != null) {
            forceLoad();
        }

    }


    @Override
    public List<Weather> loadInBackground() {
     return  insertWeatherInfo.readDatabaseWeatherInfo();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        mData = null;

    }

    @Override
    public void deliverResult(List<Weather> data) {
            super.deliverResult(data);


    }
}
