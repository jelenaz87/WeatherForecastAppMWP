package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jelenazivanovic.weatherforecastappmwp.R;
import com.example.jelenazivanovic.weatherforecastappmwp.data.Weather;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.DaggerRecyclerViewComponent;
import com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.view.SettingsActivity;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.ActivityContextModule;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di.RecyclerViewModule;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.di.DaggerMountainViewComponent;


import java.util.List;

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

       DaggerRecyclerViewComponent.builder().recyclerViewModule(new RecyclerViewModule(this)).activityContextModule(new ActivityContextModule(getApplicationContext())).build().inject(this);

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
    public void lisOfWeather(List<Weather>list) {

        mAdapter.swapCursor(list);
        mRecyclerView.setAdapter(mAdapter);
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
}
