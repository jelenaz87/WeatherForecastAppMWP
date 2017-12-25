package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view;


import android.content.SharedPreferences;
import android.support.v7.preference.Preference;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public interface SettingsFragmentView {
    void getResults(Preference preference, Object value);
    void getResultOnSharedPreferenceChangeListenerPresenter (SharedPreferences preferences, String key);

}
