package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity;

import android.support.annotation.NonNull;

import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jelena.zivanovic on 12/27/2017.
 */

public class ApiServiceClient {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_URL_MOUNTAIN_VIEW = "https://andfun-weather.udacity.com/";
    private static ServiceApi mServiceApi;
    private static MountainViewApi mountainViewApi;


    public static ServiceApi getResponseFromServiceApiForBelgrade() {

        if (mServiceApi == null) {
           mServiceApi = getRetrofitForBlegrade().create(ServiceApi.class);
        }
        return mServiceApi;
    }

    public static MountainViewApi getResponseFromServiceApiForMountainView() {

        if (mountainViewApi == null) {
            mountainViewApi = getRetrofitForMountainView().create(MountainViewApi.class);
        }
        return mountainViewApi;
    }

    private static Retrofit getRetrofitForBlegrade() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Retrofit getRetrofitForMountainView() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MOUNTAIN_VIEW)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
