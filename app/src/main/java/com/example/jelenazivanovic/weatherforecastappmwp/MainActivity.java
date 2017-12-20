package com.example.jelenazivanovic.weatherforecastappmwp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.WeatherObj;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.di.DaggerRecyclerViewComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.view.ForecastAdapter;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.view.RecyclerViewView;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.ForecastAdapterOnClickHandler, RecyclerViewView {

    private RecyclerView mRecyclerView;
    private ForecastAdapter mAdapter;


    @Inject
    RecyclerViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerRecyclerViewComponent.builder().recyclerViewModule( new RecyclerViewModule(this)).build().inject(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_forecast);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ForecastAdapter(this, this);
        presenter.invokePresenter();

    }

    @Override
    public void onClick(long date) {

    }

    @Override
    public void lisOfWeather(ArrayList<WeatherObj> list) {
        mAdapter.swapCursor(list);
        mRecyclerView.setAdapter(mAdapter);

    }


//    {
//            "Today, May 17 - Clear - 17°C / 15°C",
//            "Tomorrow - Cloudy - 19°C / 15°C",
//            "Thursday - Rainy- 30°C / 11°C",
//            "Friday - Thunderstorms - 21°C / 9°C",
//            "Saturday - Thunderstorms - 16°C / 7°C",
//            "Sunday - Rainy - 16°C / 8°C",
//            "Monday - Partly Cloudy - 15°C / 10°C",
//            "Tue, May 24 - Meatballs - 16°C / 18°C",
//            "Wed, May 25 - Cloudy - 19°C / 15°C",
//            "Thu, May 26 - Stormy - 30°C / 11°C",
//            "Fri, May 27 - Hurricane - 21°C / 9°C",
//            "Sat, May 28 - Meteors - 16°C / 7°C",
//            "Sun, May 29 - Apocalypse - 16°C / 8°C",
//            "Mon, May 30 - Post Apocalypse - 15°C / 10°C",
//    };



}
