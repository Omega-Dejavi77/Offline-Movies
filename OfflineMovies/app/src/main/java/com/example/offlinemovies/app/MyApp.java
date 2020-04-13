package com.example.offlinemovies.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    public static MyApp instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }
}
