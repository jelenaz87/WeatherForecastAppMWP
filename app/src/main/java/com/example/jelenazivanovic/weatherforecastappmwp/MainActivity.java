package com.example.jelenazivanovic.weatherforecastappmwp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.WeatherObj;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.ActivityContextModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.DaggerRecyclerViewComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.ForecastAdapter;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;

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

        DaggerRecyclerViewComponent.builder().recyclerViewModule( new RecyclerViewModule(this)).activityModule(new ActivityContextModule(getApplicationContext())).build().inject(this);

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

}
