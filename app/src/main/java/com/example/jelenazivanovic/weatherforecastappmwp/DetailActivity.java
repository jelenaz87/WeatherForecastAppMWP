/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.Intent;
import android.database.Cursor;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jelenazivanovic.weatherforecastappmwp.databinding.ActivityDetailBinding;


public class DetailActivity extends AppCompatActivity{

    /*
     * In this Activity, you can share the selected day's forecast. No social sharing is complete
     * without using a hashtag. #BeTogetherNotTheSame
     */
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    /*
     * The columns of data that we are interested in displaying within our DetailActivity's
     * weather display.
     */


    /*
     * We store the indices of the values in the array of Strings above to more quickly be able
     * to access the data from our query. If the order of the Strings above changes, these
     * indices must be adjusted to match the order of the Strings.
     */


    /*
     * This ID will be used to identify the Loader responsible for loading the weather details
     * for a particular day. In some cases, one Activity can deal with many Loaders. However, in
     * our case, there is only one. We will still use this ID to initialize the loader and create
     * the loader for best practice. Please note that 353 was chosen arbitrarily. You can use
     * whatever number you like, so long as it is unique and consistent.
     */


    /* A summary of the forecast that can be shared by clicking the share button in the ActionBar */
    private String mForecastSummary;

    /* The URI that is used to access the chosen day's weather details */
    private Uri mUri;

//  TODO (2) Remove all the TextView declarations
//    private TextView mDateView;
//    private TextView mDescriptionView;
//    private TextView mHighTemperatureView;
//    private TextView mLowTemperatureView;
//    private TextView mHumidityView;
//    private TextView mWindView;
//    private TextView mPressureView;

    /*
     * This field is used for data binding. Normally, we would have to call findViewById many
     * times to get references to the Views in this Activity. With data binding however, we only
     * need to call DataBindingUtil.setContentView and pass in a Context and a layout, as we do
     * in onCreate of this class. Then, we can access all of the Views in our layout
     * programmatically without cluttering up the code with findViewById.
     */
//  TODO (3) Declare an ActivityDetailBinding field called mDetailBinding
    private ActivityDetailBinding mDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      TODO (4) Remove the call to setContentView
      //  setContentView(R.layout.activity_detail);

//      TODO (5) Remove all the findViewById calls
//        mDateView = (TextView) findViewById(R.id.date);
//        mDescriptionView = (TextView) findViewById(R.id.weather_description);
//        mHighTemperatureView = (TextView) findViewById(R.id.high_temperature);
//        mLowTemperatureView = (TextView) findViewById(R.id.low_temperature);
//        mHumidityView = (TextView) findViewById(R.id.humidity);
//        mWindView = (TextView) findViewById(R.id.wind);
//        mPressureView = (TextView) findViewById(R.id.pressure);

//      TODO (6) Instantiate mDetailBinding using DataBindingUtil
        mDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);




        mUri = getIntent().getData();
        if (mUri == null) throw new NullPointerException("URI for DetailActivity cannot be null");

        /* This connects our Activity into the loader lifecycle. */

    }

    /**
     * This is where we inflate and set up the menu for this Activity.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     *
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
//        MenuInflater inflater = getMenuInflater();
//        /* Use the inflater's inflate method to inflate our menu layout to this menu */
//        inflater.inflate(R.menu.detail, menu);
//        /* Return true so that the menu is displayed in the Toolbar */
//        return true;
//    }

    /**
     * Callback invoked when a menu item was selected from this Activity's menu. Android will
     * automatically handle clicks on the "up" button for us so long as we have specified
     * DetailActivity's parent Activity in the AndroidManifest.
     *
     * @param item The menu item that was selected by the user
     *
     * @return true if you handle the menu click here, false otherwise
     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        /* Get the ID of the clicked item */
//        int id = item.getItemId();
//
//        /* Settings menu item clicked */
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
//
//        /* Share menu item clicked */
//        if (id == R.id.action_share) {
//            Intent shareIntent = createShareForecastIntent();
//            startActivity(shareIntent);
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing.  All we need
     * to do is set the type, text and the NEW_DOCUMENT flag so it treats our share as a new task.
     * See: http://developer.android.com/guide/components/tasks-and-back-stack.html for more info.
     *
     * @return the Intent to use to share our weather forecast
     */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecastSummary + FORECAST_SHARE_HASHTAG)
                .getIntent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        return shareIntent;
    }







}