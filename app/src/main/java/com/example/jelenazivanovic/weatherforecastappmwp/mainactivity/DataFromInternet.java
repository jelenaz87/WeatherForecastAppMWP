package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.Call;

import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.data.CityWeatherData;
import com.example.jelenazivanovic.weatherforecastappmwp.data.SunshinePreferences;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherObject;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherInfo;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.models.WeatherMountainView;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineDateUtils;
import com.example.jelenazivanovic.weatherforecastappmwp.utilities.SunshineWeatherUtils;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by jelena.zivanovic on 12/27/2017.
 */

public class DataFromInternet {


   private WeatherObject weatherObjectBelgrade;
   private WeatherMountainView  weatherObjectMountainView;
    private List weatherList;
    private int i ;
    private int y ;


    public DataFromInternet () {
        this.i = 0;
        this.y = 0;
        this.weatherObjectBelgrade = null;
        this.weatherObjectMountainView = null;
        this.weatherList = new ArrayList();

    }

    public Observable<List<Weather>> getDataForBelgrade (final Context mContext) {

        Observable<WeatherObject> objectCall = ApiServiceClient.getResponseFromServiceApiForBelgrade().getWeatherObject("Belgrade,688","88679d8457078f98d550b4e24ac9ac26");

         return objectCall.map(new Function<WeatherObject, List<Weather>>() {
             @Override
             public List<Weather> apply(WeatherObject weatherObject) throws Exception {
              return getObservableListForBelgrade(weatherObject,mContext).blockingGet();

             }

         });
    }

    public Observable<List<Weather>> getDataForMountainView (final Context mContext) {

        Observable<WeatherMountainView> objectCall = ApiServiceClient.getResponseFromServiceApiForMountainView().getWeather();

        return objectCall.map(new Function<WeatherMountainView, List<Weather>>() {
            @Override
            public List<Weather> apply(WeatherMountainView weatherMountainView) throws Exception {
                return getWeatherInfoFromWeathrObject(weatherMountainView, mContext).blockingGet();
            }
        });
    }

    public Observable<List<Weather>> getDataFromInternet (String city, Context context) {
        return getObservableForBelgrade(context);

//       if (city.equalsIgnoreCase("Belgrade")) {
//           return getObservableForBelgrade(context);
//       } else if (city.equalsIgnoreCase("MountainView")) {
//          return getObservableForMountainView(context);
//       }
//       return null;

    }

    public Observable<List<Weather>> getObservableForMountainView(Context context){
        return  getDataForMountainView(context);
    }

    public Observable<List<Weather>> getObservableForBelgrade(Context context) {
        return getDataForBelgrade(context);
    }

    private Single<List<Weather>> getWeatherInfoFromWeathrObject (WeatherMountainView weatherMountainView, final Context mContext) {
        return Observable.fromIterable(weatherMountainView.getList()).map(new io.reactivex.functions.Function<WeatherInfo, Weather>() {
            @Override
            public Weather apply(WeatherInfo weatherInfo) throws Exception {
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

                float pressure = weatherInfo.getPressure();
                String pressureString = mContext.getString(R.string.format_pressure, pressure);

                float humidity = weatherInfo.getHumidity();
                String humidityString = mContext.getString(R.string.format_humidity, humidity);

                float windSpeed = weatherInfo.getWindSpeed();
                float windDirection = weatherInfo.getWindDirection();
                String windString = SunshineWeatherUtils.getFormattedWind(mContext, windSpeed, windDirection);

            //    weather = new Weather(i,dateString, weatherId, minTemp, maxTemp, pressureString, humidityString,windString, description);
                i++;
                return weather;
            }
        }).toList();


        }



    private Single<List<Weather>> getObservableListForBelgrade (WeatherObject weatherObject, final Context mContext) {

        return Observable.fromIterable(weatherObject.getList()).map(new Function<com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherInfo, Weather>() {
            @Override
            public Weather apply(com.example.jelenazivanovic.weatherforecastappmwp.retrofit.models.WeatherInfo weatherInfo) throws Exception {
                Weather weather = new Weather();
                String location = SunshinePreferences.getWeatherLocation(mContext);
                weather.setCityName(location);
                CityWeatherData data = null;
                long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
                if (i ==0 || i == 8 || i == 16 || i == 24 || i == 32 ) {
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

                    float pressure = weatherInfo.getTemperatureObject().getPressure();
                    String pressureString = mContext.getString(R.string.format_pressure, pressure);

                    float humidity = weatherInfo.getTemperatureObject().getHumidity();
                    String humidityString = mContext.getString(R.string.format_humidity, humidity);

                    float windSpeed = weatherInfo.getWindInfo().getWindSpeed();
                    float windDirection = weatherInfo.getWindInfo().getWindDirection();
                    String windString = SunshineWeatherUtils.getFormattedWind(mContext, windSpeed, windDirection);

                    data = new CityWeatherData(y, dateString, weatherId, minTemp, maxTemp, pressureString, humidityString, windString, description);
                    weather.setCityObject(data);
                    weather.setChangedLocation(false);

                    y++;
                } else {
                    weather = new Weather();
                }
                i++;
                return weather;
            }
        }).filter(new Predicate<Weather>() {
            @Override
            public boolean test(Weather weather) throws Exception {
                return weather.isEmptyWeather(weather);
            }
        }).toList();

    }





}