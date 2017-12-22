package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.di;

import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.presenter.SettingsFragmentPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view.SettingsFragmentView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/22/2017.
 */
@Module
public class SettingsFragmentModule {

    private SettingsFragmentView view;

    public SettingsFragmentModule(SettingsFragmentView view) {

        this.view = view;
    }

    @Provides
    SettingsFragmentView getView () {
        return view;
    }

    @Provides
    SettingsFragmentPresenter getSettingsPresenter () {
        return new SettingsFragmentPresenterImpl(view);
    }
}
