package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jelena.zivanovic on 12/27/2017.
 */

public class DataFromInternet {


   private WeatherObject weatherObjectBelgrade;
   private WeatherMountainView  weatherObjectMountainView;
    private List weatherList;
    private int i = 0;
    private int y = 0;


    public DataFromInternet () {

        this.weatherObjectBelgrade = null;
        this.weatherObjectMountainView = null;
        this.weatherList = new ArrayList();

    }

    public Observable<List<Weather>> getDataForBelgrade (final Context mContext) {
        Observable<WeatherObject> objectCall = ApiServiceClient.getResponseFromServiceApiForBelgrade().getWeatherObject();
//        objectCall.enqueue(new Callback<WeatherObject>() {
//            @Override
//            public void onResponse(Call<WeatherObject> call, Response<WeatherObject> response) {
//              weatherObjectBelgrade = response.body();
//            //  isResponseSuccesfull.getResponse(weatherObjectBelgrade);
//            }
//
//            @Override
//            public void onFailure(Call<WeatherObject> call, Throwable t) {
//                String s = "failure";
//            }
//        });
         return objectCall.map(new Func1<WeatherObject, List<Weather>>() {
             @Override
             public List<Weather> call(WeatherObject weatherObject) {
                 List<Weather> list =Observable.from(getObservableListForBelgrade(weatherObject,mContext).toBlocking().single()).filter(new Func1<Weather, Boolean>() {
                     @Override
                     public Boolean call(Weather weather) {

                         return weather != null;
                     }
                 }).toList().toBlocking().single();
                 return list;

             }
         });
    }

    public Observable<List<Weather>> getDataForMountainView (final Context mContext) {

        Observable<WeatherMountainView> objectCall = ApiServiceClient.getResponseFromServiceApiForMountainView().getWeather();

//        objectCall.enqueue(new Callback<WeatherMountainView>() {
//          @Override
//          public void onResponse(Call<WeatherMountainView> call, Response<WeatherMountainView> response) {
//              if (response.isSuccessful()) {
//                  weatherObjectMountainView = response.body();
//                  isResponseSuccesfull.getResponse(weatherObjectMountainView);
//              }
//          }
//
//          @Override
//          public void onFailure(Call<WeatherMountainView> call, Throwable t) {
//            String s = "failure";
//          }
//      });

        return objectCall.map(new Func1<WeatherMountainView,List<Weather>>() {
            @Override
            public List<Weather> call(WeatherMountainView weatherMountainView) {
                return getWeatherInfoFromWeathrObject (weatherMountainView,mContext).toBlocking().single();            }
        });
    }

    public Observable<List<Weather>> getDataFromInternet (String city, Context context) {
       if (city.equalsIgnoreCase("Belgrade")) {
           return getObservableForBelgrade(context);
       } else if (city.equalsIgnoreCase("MountainView")) {
          return getObservableForMountainView(context);
       }
       return null;

    }

    public Observable<List<Weather>> getObservableForMountainView(Context context){
        return  getDataForMountainView(context);
    }

    public Observable<List<Weather>> getObservableForBelgrade(Context context) {
        return getDataForBelgrade(context);
    }

    private Observable<List<Weather>> getWeatherInfoFromWeathrObject (WeatherMountainView weatherMountainView, final Context mContext) {
        return Observable.from(weatherMountainView.getList()).map(new Func1<WeatherInfo, Weather>() {
            @Override
            public Weather call(WeatherInfo weatherInfo) {
                Weather weather = null;

                long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
                long dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * i;
                String dateString = SunshineDateUtils.getFriendlyDateString(mContext, dateTimeMillis, false);
                int weatherId = weatherInfo.getWeatherDetail().get(0).getWeatherId();
                String description = SunshineWeatherUtils.getStringForWeatherCondition(mContext, weatherId);

                double tempMinInCelsius = weatherInfo.getTemperatureObject().getMinTemperature();

                String minTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMinInCelsius);

                double tempMaxInCelsius = weatherInfo.getTemperatureObject().getMaxTemperature();

                String maxTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMaxInCelsius);

                double pressure = weatherInfo.getPressure();
                int humidity = weatherInfo.getHumidity();
                double windSpeed = weatherInfo.getWindSpeed();
                double windDirection = weatherInfo.getWindDirection();

                weather = new Weather(dateString, weatherId, minTemp, maxTemp, pressure, humidity, windSpeed, windDirection, description);
                i++;
                return weather;
            }
        }).toList();

    }

    private Observable<List<Weather>> getObservableListForBelgrade (WeatherObject weatherObject, final Context mContext) {
        return Observable.from(weatherObject.getList()).map(new Func1<com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherInfo, Weather>() {
            @Override
            public Weather call(com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherInfo weatherInfo) {
                Weather weather = null;
                long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
                if (i ==0 || i == 7 || i == 14 || i == 21 || i == 28 || i == 35 ) {
                    long dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * y;
                    String dateString = SunshineDateUtils.getFriendlyDateString(mContext, dateTimeMillis, false);
                    int weatherId = weatherInfo.getWeatherDetail().get(0).getWeatherId();
                    String description = SunshineWeatherUtils.getStringForWeatherCondition(mContext, weatherId);

                    double tempMinInFahrenheit = weatherInfo.getTemperatureObject().getMinTemperature();
                    double tempMinInCelsius = tempMinInFahrenheit - 273.15;
                    String minTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMinInCelsius);

                    double tempMaxInFahrenheit = weatherInfo.getTemperatureObject().getMaxTemperature();
                    double tempMaxInCelsius = tempMaxInFahrenheit - 273.15;
                    String maxTemp = SunshineWeatherUtils.formatTemperature(mContext, tempMaxInCelsius);

                    double pressure = weatherInfo.getTemperatureObject().getPressure();
                    int humidity = weatherInfo.getTemperatureObject().getHumidity();
                    double windSpeed = weatherInfo.getWindInfo().getWindSpeed();
                    double windDirection = weatherInfo.getWindInfo().getWindDirection();

                    weather = new Weather(dateString, weatherId, minTemp, maxTemp, pressure, humidity, windSpeed, windDirection, description);

                    y++;
                }
                i++;
                return weather;
            } }).toList();
    }





}
