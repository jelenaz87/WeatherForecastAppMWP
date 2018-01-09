package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by jelena.zivanovic on 12/27/2017.
 */

public interface IsResponseSuccesfull {

    void getResponse (Observable<List<Weather>> listObservable);
}
