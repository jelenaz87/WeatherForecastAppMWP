package com.example.jelenazivanovic.weatherforecastappmwp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by jelena.zivanovic on 1/10/2018.
 */

public class MyReceiver extends BroadcastReceiver {

    ListAdapterAsyncTaskLoader loader;

    public MyReceiver(ListAdapterAsyncTaskLoader loader) {
        this.loader = loader;
        IntentFilter filter = new IntentFilter();
        loader.getContext().registerReceiver(this, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
         loader.onContentChanged();
    }
}
