package com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.di;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jelena.zivanovic on 12/26/2017.
 */
@Module
public class RetrofitModuleMountainView {

    public static final String BASE_URL_MOUNTAIN_VIEW = "https://andfun-weather.udacity.com/";

    @Provides
    Retrofit getRetrofit () {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MOUNTAIN_VIEW)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    MountainViewApi getApiMountainView (Retrofit retrofit) {
        return retrofit.create(MountainViewApi.class);
    }

}
