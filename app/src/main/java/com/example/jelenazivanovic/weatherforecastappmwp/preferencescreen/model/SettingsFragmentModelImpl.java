package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model;


import android.content.Context;
import android.content.SharedPreferences;

import android.support.v7.preference.CheckBoxPreference;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public class SettingsFragmentModelImpl implements SettingsFragmentModel {

    private SettingsFragmentPresenter presenter;

    public SettingsFragmentModelImpl(SettingsFragmentPresenter presenter) {
        this.presenter = presenter;
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
    public void sendResultOnSharedPreferenceChangeListenerToModel(String key, Context mContext, SharedPreferences preferences) {
        if (key.equals(mContext.getString(R.string.pref_location_key))) {

        } else if (key.equals(mContext.getString(R.string.pref_units_key))) {

        }

        presenter.getResultOnSharedPreferenceChangeListener(preferences, key);
    }

}
