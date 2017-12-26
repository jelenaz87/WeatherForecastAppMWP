package com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.di;

import android.content.Context;

import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.presenter.RecyclerViewPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.mainactivity.view.RecyclerViewView;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofit.apiservice.ServiceApi;
import com.example.jelenazivanovic.weatherforecastappmwp.retrofitmountaintview.serviceApiMountainView.MountainViewApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/20/2017.
 */
@Module
public class RecyclerViewModule {

    private RecyclerViewView view;

    public RecyclerViewModule (RecyclerViewView view) {
      this.view = view;
    }

    @Provides
    RecyclerViewView getView () {
        return  view;
    }

    @Provides
    RecyclerViewPresenter getRecyclerViewPresenter (RecyclerViewView view, MountainViewApi api, Context mContext) {
        return new RecyclerViewPresenterImpl(view, api, mContext);
    }


}
