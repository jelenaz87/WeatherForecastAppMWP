package com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.di;

import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter.RecyclerViewPresenter;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.presenter.RecyclerViewPresenterImpl;
import com.example.jelenazivanovic.weatherforecastappmwp.firstscreen.view.RecyclerViewView;

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
    RecyclerViewPresenter getRecyclerViewPresenter (RecyclerViewView view) {
        return new RecyclerViewPresenterImpl(view);
    }


}
