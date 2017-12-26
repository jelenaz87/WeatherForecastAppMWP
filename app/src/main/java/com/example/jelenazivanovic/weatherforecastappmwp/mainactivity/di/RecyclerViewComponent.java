package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.MainActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.di.RetrofitModule;

import dagger.Component;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */
@Component (modules = {RecyclerViewModule.class, RetrofitModule.class, ActivityContextModule.class})
public interface RecyclerViewComponent {

   // void inject (MainActivity mainActivity);
}
