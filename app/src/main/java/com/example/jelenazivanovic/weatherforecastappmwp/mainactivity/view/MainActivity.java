package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jelenazivanovic.weatherforecastappmwp.ListAdapterAsyncTaskLoader;
import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;

import com.example.jelenazivanovic.weatherforecastappmwp.detailactivity.view.DetailActivity;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.DaggerRecyclerViewComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view.SettingsActivity;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.ActivityContextModule;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.ForecastAdapterOnClickHandler, RecyclerViewView, LoaderManager.LoaderCallbacks<List<Weather>> {

    private RecyclerView mRecyclerView;
    private ForecastAdapter mAdapter;

    private LoaderManager.LoaderCallbacks<List<Weather>> mLoader = new LoaderManager.LoaderCallbacks<List<Weather>>() {
        @Override
        public Loader<List<Weather>> onCreateLoader(int id, Bundle args) {
            return new ListAdapterAsyncTaskLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<Weather>> loader, List<Weather> data) {
           mAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Weather>> loader) {

        }
    };

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

        getSupportLoaderManager().initLoader(0, null, mLoader);
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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.forecast,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<List<Weather>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Weather>> loader, List<Weather> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Weather>> loader) {

    }


}
