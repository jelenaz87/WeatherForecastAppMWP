package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.di;

import com.example.jelenazivanovic.weatherforecastappmwp.SettingsFragment;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/21/2017.
 */
@Component (modules = FragmentContextModule.class)
public interface SettingsFragmentComponent {
    void inject (SettingsFragment fragment);
}
