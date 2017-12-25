package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;


import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model.SettingsFragmentModel;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model.SettingsFragmentModelImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view.SettingsFragmentView;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public class SettingsFragmentPresenterImpl implements SettingsFragmentPresenter{

    private SettingsFragmentView view;
    private SettingsFragmentModel model;

    public SettingsFragmentPresenterImpl(SettingsFragmentView view) {
       this.view = view;
       this.model = new SettingsFragmentModelImpl(this);

    }



    @Override
    public void provideToPresenter(SharedPreferences preferences, ArrayList<Preference> list) {
        model.sendSharedPreferenceAndPreference(preferences, list);
    }

    @Override
    public void getValueForPreferenceScreen(Preference preference, Object value) {
        view.getResults(preference,value);

    }

    @Override
    public void sendKey(String key, Context mContext, SharedPreferences preferences) {
      model.sendResultOnSharedPreferenceChangeListenerToModel(key,mContext, preferences);
    }

    @Override
    public void getResultOnSharedPreferenceChangeListener(SharedPreferences preferences, String key) {
        view.getResultOnSharedPreferenceChangeListenerPresenter(preferences, key);

    }

}
