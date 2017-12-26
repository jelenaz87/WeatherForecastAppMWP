package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */

public class RecyclerViewModelImpl  implements RecyclerViewModel {

    private RecyclerViewPresenter presenter;
   // private ServiceApi mServiceApi;
    private Context mContext;
    private MountainViewApi api;

    public RecyclerViewModelImpl(RecyclerViewPresenter presenter, MountainViewApi api, Context mContext) {
        this.presenter = presenter;
        this.api = api;
        this.mContext = mContext;
    }


    @Override
    public void getWeatherResults() {

       Call<WeatherMountainView> objectCall = api.getWeather();
       objectCall.enqueue(new Callback<WeatherMountainView>() {
          @Override
          public void onResponse(Call<WeatherMountainView> call, Response<WeatherMountainView> response) {
              WeatherMountainView object = response.body();
              presenter.updateWeather(object);
          }

          @Override
          public void onFailure(Call<WeatherMountainView> call, Throwable t) {
            
          }
      });




    }
}
