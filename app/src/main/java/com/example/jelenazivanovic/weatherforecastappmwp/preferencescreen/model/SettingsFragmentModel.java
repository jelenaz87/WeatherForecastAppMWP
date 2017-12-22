package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.model;

import android.content.SharedPreferences;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import java.util.ArrayList;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public interface SettingsFragmentModel {

    void sendSharedPreferenceAndPreference (SharedPreferences sharedPreferences, ArrayList<Preference> list);
    void sendSharedPreferenceAndKey (SharedPreferences preferences, String key);

}
