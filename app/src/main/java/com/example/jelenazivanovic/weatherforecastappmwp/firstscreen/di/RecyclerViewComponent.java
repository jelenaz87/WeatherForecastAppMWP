package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.di;

import com.example.jelenazivanovic.weatherforecastappmwp.MainActivity;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */
@Component (modules = {RecyclerViewModule.class})
public interface RecyclerViewComponent {

    void inject (MainActivity mainActivity);
}
