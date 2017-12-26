package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.di;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.ActivityContextModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.MainActivity;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/26/2017.
 */
@Component (modules = {RecyclerViewModule.class, RetrofitModuleMountainView.class, ActivityContextModule.class})
public interface MountainViewComponent {

    void provide (MainActivity mainActivity);
}
