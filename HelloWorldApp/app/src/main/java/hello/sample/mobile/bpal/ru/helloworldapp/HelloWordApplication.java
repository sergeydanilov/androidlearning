package hello.sample.mobile.bpal.ru.helloworldapp;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by serg on 27.04.16.
 */
public class HelloWordApplication extends Application {
    public static final String TAG_PREFIX = "TP_";
    private final String TAG = TAG_PREFIX + this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        // This is a place to put code that must manage storage across
        // multiple activities, but it's better to keep most things in a
        // database, rather than in memory
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "onTerminate");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // In-memory caches should be thrown overboard here
        Log.i(TAG, "onLowMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConifgurationChanged");
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, newConfig.toString());
        }

    }

}
