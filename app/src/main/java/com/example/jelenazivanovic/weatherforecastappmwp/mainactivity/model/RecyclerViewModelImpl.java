package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.SharedPreferences;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel {

    private RecyclerViewPresenter presenter;
    private ServiceApi mServiceApi;

    public RecyclerViewModelImpl(RecyclerViewPresenter presenter, ServiceApi mServiceApi) {
        this.presenter = presenter;
        this.mServiceApi = mServiceApi;
    }


    @Override
    public void getWeatherResults() {

       Call<WeatherObject> objectCall = mServiceApi.getWeatherObject();
       objectCall.enqueue(new Callback<WeatherObject>() {
           @Override
           public void onResponse(Call<WeatherObject> call, Response<WeatherObject> response) {
            WeatherObject object = response.body();
            presenter.updateWeatherInfo(object);

           }

           @Override
           public void onFailure(Call<WeatherObject> call, Throwable t) {

           }
       });




    }
}
