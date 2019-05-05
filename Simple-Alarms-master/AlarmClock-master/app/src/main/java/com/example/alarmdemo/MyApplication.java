package com.example.alarmdemo;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class MyApplication extends Application {
    private static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized MyApplication get() {
        return app;
    }

}
