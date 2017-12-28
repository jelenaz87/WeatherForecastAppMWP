package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.IsResponseSuccesfull;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jelena.zivanovic on 12/27/2017.
 */

public class DataFromInternet {


   private WeatherObject weatherObjectBelgrade;
   private WeatherMountainView  weatherObjectMountainView;
   private IsResponseSuccesfull isResponseSuccesfull;

    public DataFromInternet() {


    }
    public DataFromInternet (IsResponseSuccesfull responseSuccesfull) {

        this.weatherObjectBelgrade = null;
        this.weatherObjectMountainView = null;
        this.isResponseSuccesfull = responseSuccesfull;

    }

    public void getDataForBelgrade () {
        Call<WeatherObject> objectCall = ApiServiceClient.getResponseFromServiceApiForBelgrade().getWeatherObject();
        objectCall.enqueue(new Callback<WeatherObject>() {
            @Override
            public void onResponse(Call<WeatherObject> call, Response<WeatherObject> response) {
              weatherObjectBelgrade = response.body();
              isResponseSuccesfull.getResponse(weatherObjectBelgrade);
            }

            @Override
            public void onFailure(Call<WeatherObject> call, Throwable t) {
                String s = "failure";
            }
        });

    }

    public void getDataForMountainView () {

        Call<WeatherMountainView> objectCall = ApiServiceClient.getResponseFromServiceApiForMountainView().getWeather();
        objectCall.enqueue(new Callback<WeatherMountainView>() {
          @Override
          public void onResponse(Call<WeatherMountainView> call, Response<WeatherMountainView> response) {
              if (response.isSuccessful()) {
                  weatherObjectMountainView = response.body();
                  isResponseSuccesfull.getResponse(weatherObjectMountainView);
              }
          }

          @Override
          public void onFailure(Call<WeatherMountainView> call, Throwable t) {
            String s = "failure";
          }
      });
    }

    public void getDataFromInternet (String city) {
       if (city.equalsIgnoreCase("Belgrade")) {
           getDataForBelgrade();
       } else if (city.equalsIgnoreCase("MountainView")) {
           getDataForMountainView();
       }

    }





}
