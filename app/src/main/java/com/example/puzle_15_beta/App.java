package com.example.puzle_15_beta;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCache.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
