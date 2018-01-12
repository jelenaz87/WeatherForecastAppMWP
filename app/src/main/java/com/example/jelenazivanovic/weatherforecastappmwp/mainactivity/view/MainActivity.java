package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.RecyclerViewModel;

import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;


import com.example.jelenazivanovic.weatherforecastappmwp.detailactivity.view.DetailActivity;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.DaggerRecyclerViewComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view.SettingsActivity;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.ActivityContextModule;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;


import java.util.List;

import javax.inject.Inject;



public class MainActivity extends AppCompatActivity implements ForecastAdapter.ForecastAdapterOnClickHandler, RecyclerViewView {
    private RecyclerView mRecyclerView;
    private ForecastAdapter mAdapter;

    private RecyclerViewModel model;

    @Inject
    RecyclerViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DaggerRecyclerViewComponent.builder().recyclerViewModule(new RecyclerViewModule(this)).activityContextModule(new ActivityContextModule(getApplicationContext())).build().inject(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_forecast);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ForecastAdapter(this, this);
        presenter.invokePresenter();

       // model = ViewModelProviders.of(this).get(RecyclerViewModel.class);
        mRecyclerView.setVisibility(View.INVISIBLE);

//        model.getItemAndPersonList().observe(MainActivity.this, new android.arch.lifecycle.Observer<List<Weather>>() {
//            @Override
//            public void onChanged(@Nullable List<Weather> weatherList) {
//                mAdapter.swapCursor(weatherList);
//                mRecyclerView.setVisibility(View.VISIBLE);
//            }
//        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.checkStateOfDatabase();
    }

    @Override
    public void onClick(int id) {
        presenter.sendIdOfRow(id);
    }

    @Override
    public void lisOfWeather(List<Weather>list) {

        mAdapter.swapCursor(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getWeatherFromOneRow(Weather weather) {
        Intent weatherDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
        weatherDetailIntent.putExtra("weather", weather);
        startActivity(weatherDetailIntent);
    }

    @Override
    public void updateUi(List<Weather> mlist) {
        mAdapter.swapCursor(mlist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.forecast,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent,2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
