package com.example.jelenazivanovic.weatherforecastappmwp.retrofit.di;

import android.support.annotation.NonNull;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jelena.zivanovic on 12/21/2017.
 */
@Module
public class RetrofitModule {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";


    @Provides
    Retrofit getRetrofit () {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    ServiceApi getServiceApi (Retrofit retrofit) {
            return retrofit.create(ServiceApi.class);
        }

    }


