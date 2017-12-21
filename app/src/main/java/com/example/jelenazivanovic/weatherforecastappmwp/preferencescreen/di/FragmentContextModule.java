package com.example.jelenazivanovic.weatherforecastappmwp.preferencescreen.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jelena.zivanovic on 12/21/2017.
 */
@Module
public class FragmentContextModule {

    private Context mContext;

    public FragmentContextModule(Context context) {
        this.mContext = context;
    }

    @Provides Context getmContext () {
        return this.mContext;
    }
}
