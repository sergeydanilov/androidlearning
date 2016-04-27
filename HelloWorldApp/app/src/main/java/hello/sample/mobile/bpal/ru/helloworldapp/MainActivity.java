package hello.sample.mobile.bpal.ru.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final String TAG = HelloWordApplication.TAG_PREFIX + this.getClass().getSimpleName();
    private final String RESTORE = ", can restore state";

    private final String state = "Моя Умная Строка";
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        counter++;
        setContentView(R.layout.activity_main);

        String answer = null;
        // savedState could be null
        if (null != savedState) {
            answer = savedState.getString("answer");
        }
        Log.i(TAG, "onCreate: " + " : " +counter +" : "
                + (null == savedState ? "" : (RESTORE + " " + answer)));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Notification that the activity will be started
        Log.i(TAG, "onRestart" + " : " +counter +" : ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Notification that the activity is starting
        Log.i(TAG, "onStart" + " : " +counter +" : ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Notification that the activity will interact with the user
        Log.i(TAG, "onResume" + " : " +counter +" : ");
    }

    protected void onPause() {
        super.onPause();
        // Notification that the activity will stop interacting with the user
        Log.i(TAG, "onPause"  + " : " +counter +" : "+ (isFinishing() ? " Finishing" : ""));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Notification that the activity is no longer visible
        Log.i(TAG, "onStop" + " : " +counter +" : ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Notification the activity will be destroyed
        Log.i(TAG,
                "onDestroy " + " : " +counter +" : "
                        // Log which, if any, configuration changed
                        + Integer.toString(getChangingConfigurations(), 16));
    }

    // ////////////////////////////////////////////////////////////////////////////
    // Called during the lifecycle, when instance state should be saved/restored
    // ////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save instance-specific state
        outState.putString("answer", state);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState" + " : " +counter +" : ");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        String answer = null != savedState ? savedState.getString("answer") : "";

        Log.i(TAG, "onRestoreInstanceState" + " : " +counter +" : "
                + (null == savedState ? "" : RESTORE) + " " + answer);
    }

    // ////////////////////////////////////////////////////////////////////////////
    // These are the minor lifecycle methods, you probably won't need these
    // ////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onPostCreate(Bundle savedState) {
        super.onPostCreate(savedState);
        String answer = null;
        // savedState could be null
        if (null != savedState) {
            answer = savedState.getString("answer");
        }
        Log.i(TAG, "onPostCreate" + " : " +counter +" : "
                + (null == savedState ? "" : (RESTORE + " " + answer)));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume" + " : " +counter +" : ");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.i(TAG, "onUserLeaveHint" + " : " +counter +" : ");
    }
}
