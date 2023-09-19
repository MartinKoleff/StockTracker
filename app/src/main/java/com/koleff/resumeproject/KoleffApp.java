package com.koleff.resumeproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class KoleffApp extends MultiDexApplication { //implements DefaultLifecycleObserver

    public static final String TAG_LOG = "KOLEFF";

    private static Context appContext = null;
    private static Activity activeActivity = null;
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

    public static Activity getActiveActivity() {
        return activeActivity;
    }

    private static void setActiveActivity(Activity activeActivity) {
        KoleffApp.activeActivity = activeActivity;
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
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    Log.i(TAG_LOG, "Created activity: " + activity.toString());
                    if (savedInstanceState != null) {
                        //restartActivityStack(activity);
                    }
                }

                @Override
                public void onActivityStarted(Activity activity) {
                    setActiveActivity(activity);
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    setActiveActivity(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    //activeActivity = null;
                }

                @Override
                public void onActivityStopped(Activity activity) {
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    setActiveActivity(null);
                }
            });
        }
    }
}
