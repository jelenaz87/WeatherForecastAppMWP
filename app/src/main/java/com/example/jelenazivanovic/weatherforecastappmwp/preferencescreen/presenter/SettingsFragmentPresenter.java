package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;


import java.util.ArrayList;


/**
 * Created by jelena.zivanovic on 12/22/2017.
 */

public interface SettingsFragmentPresenter {
    void provideToPresenter (SharedPreferences preferences, ArrayList<Preference> list);
    void getValueForPreferenceScreen(Preference preference, Object value);
    void sendKey(String key, Context mContext, SharedPreferences preferences);
    void getResultOnSharedPreferenceChangeListener(SharedPreferences preferences, String key);
}
