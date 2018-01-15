package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model;


import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.support.v7.preference.CheckBoxPreference;

import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.data.SunshinePreferences;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.data.WeatherDatabase;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.DataFromInternet;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public class SettingsFragmentModelImpl implements SettingsFragmentModel {



    private SettingsFragmentPresenter presenter;
    private boolean isLocationChanged;


    public SettingsFragmentModelImpl(SettingsFragmentPresenter presenter) {
        this.presenter = presenter;
        this.isLocationChanged = false;

    }



    @Override
    public void sendSharedPreferenceAndPreference(SharedPreferences sharedPreferences, ArrayList<Preference> list) {

        for (int i = 0; i < list.size(); i++) {
            Preference p = list.get(i);
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                presenter.getValueForPreferenceScreen(p, (Object) value);
            }
        }

    }

    @Override
    public void sendResultOnSharedPreferenceChangeListenerToModel(final String key, final Context mContext, final SharedPreferences preferences) {



        if (key.equals(mContext.getString(R.string.pref_location_key))) {
           String location = preferences.getString(key, "");
            List<Weather> list = WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().isTableHasResultForCity(location);
            List<Weather> list1 = WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().loadAllWeatherObject();
            if (list.size() == 0 || list == null) {

                DataFromInternet data = new DataFromInternet();
                data.getDataFromInternet(location, mContext).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<List<Weather>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Weather> weatherList) {
                        for (int i = 0; i < weatherList.size(); i++) {
                            WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().insertWeatherObject(weatherList.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = "error";
                    }

                    @Override
                    public void onComplete() {
                        presenter.getResultOnSharedPreferenceChangeListener(preferences, key);
                    }
                });

            } else {

                List<Weather> mlist = WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().getValueForChangeState(true, location);

                   if (mlist.size() == 0 || mlist == null) {
                       for (int j = 0; j<list.size(); j++) {
                           list.get(j).setChangedLocation(true);
                          list.get(j).setId(list.get(j).getId());
                          WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().updateDatabase(list.get(j));
                       }

                } else {
                       for (int j = 0; j<list.size(); j++) {
                           list.get(j).setChangedLocation(false);
                           list.get(j).setId(list.get(j).getId());
                           WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().updateDatabase(list.get(j));
                       }
                   }
                presenter.getResultOnSharedPreferenceChangeListener(preferences, key);
                }


            }
        else if (key.equals(mContext.getString(R.string.pref_units_key))) {

            List<Weather> l = WeatherDatabase.getWeatherDatabaseInstance(mContext).weatherDao().loadAllWeatherObject();

            presenter.getResultOnSharedPreferenceChangeListener(preferences, key);
            }
        }
    }

