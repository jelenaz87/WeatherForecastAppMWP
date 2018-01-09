/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.jelenazivanovic.weatherforecastappmwp.R;


import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.di.DaggerSettingsFragmentComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.di.SettingsFragmentModule;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenter;


import java.util.ArrayList;

import javax.inject.Inject;


/**
 * The SettingsFragment serves as the display for all of the user's settings. In Sunshine, the
 * user will be able to change their preference for units of measurement from metric to imperial,
 * set their preferred weather location, and indicate whether or not they'd like to see
 * notifications.
 *
 * Please note: If you are using our dummy weather services, the location returned will always be
 * Mountain View, California.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener, SettingsFragmentView {

    @Inject
    SettingsFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSettingsFragmentComponent.builder().settingsFragmentModule(new SettingsFragmentModule(this)).build().inject(this);
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);

        DaggerSettingsFragmentComponent.builder().settingsFragmentModule(new SettingsFragmentModule(this)).build().inject(this);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        ArrayList<Preference> preferenceArrayList = new ArrayList<>();
        for (int i = 0; i < prefScreen.getPreferenceCount(); i++) {
            Preference preference = prefScreen.getPreference(i);
            preferenceArrayList.add(preference);
        }

        presenter.provideToPresenter(sharedPreferences, preferenceArrayList);


    }

    @Override
    public void onStop() {
        super.onStop();
        // unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // register the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        DaggerSettingsFragmentComponent.builder().settingsFragmentModule(new SettingsFragmentModule(this)).build().inject(this);
        Context mContext = getActivity().getBaseContext();
        presenter.sendKey(key, mContext, sharedPreferences);


        if (key.equals(getString(R.string.pref_location_key))) {

        } else if (key.equals(getString(R.string.pref_units_key))) {

        }
        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }
    }


    @Override
    public void getResults(Preference preference, Object value) {
        setPreferenceSummary(preference, value);

    }

    @Override
    public void getResultOnSharedPreferenceChangeListenerPresenter(SharedPreferences preferences, String key) {
        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, preferences.getString(key, ""));
            }
        }
    }
}
