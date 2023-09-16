package com.koleff.resumeproject;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class KoleffApp extends MultiDexApplication { //implements DefaultLifecycleObserver
    /**
     * Called first
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
