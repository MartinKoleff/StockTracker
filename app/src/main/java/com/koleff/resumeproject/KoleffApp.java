package com.koleff.resumeproject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class KoleffApp extends MultiDexApplication implements DefaultLifecycleObserver{

    public static final String TAG_LOG = "KOLEFF";

    private static Context appContext = null;
    private static SharedPreferences sharedPreferences = null;

    /**
     * Getters & Setters
     */

    private static void setAppContext(Context appContext) {
        KoleffApp.appContext = appContext;
    }

    public static Context getAppContext() {
        return appContext;
    }


    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences; 
    }

    private static void setSharedPreferences(SharedPreferences sharedPreferences){
        KoleffApp.sharedPreferences = sharedPreferences;
    }

    /**
     * Called first
     */
    @Override
    public void onCreate() {
        super.onCreate();

        setAppContext(getApplicationContext());

        //Cache
        setSharedPreferences(appContext.getSharedPreferences("group.Koleff", Context.MODE_PRIVATE));

        ActivityConfigurator activityConfigurator = new ActivityConfigurator();
        activityConfigurator.setupActivityListener();

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    /**
     * Activity listener
     */
    private class ActivityConfigurator {
        private void setupActivityListener() {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityStopped(Activity activity) {
                    Log.i("Tracking Activity Stopped", activity.getLocalClassName());

                }

                @Override
                public void onActivityStarted(Activity activity) {
                    Log.i("Tracking Activity Started", activity.getLocalClassName());

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    Log.i("Tracking Activity SaveInstanceState", activity.getLocalClassName());
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    Log.i("Tracking Activity Resumed", activity.getLocalClassName());
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    Log.i("Tracking Activity Paused", activity.getLocalClassName());
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    Log.i("Tracking Activity Destroyed", activity.getLocalClassName());
                }

                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    Log.i("Tracking Activity Created", activity.getLocalClassName());
                }
            });
        }
    }
}
